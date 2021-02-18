package com.klif.banking.infra;


import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import com.klif.banking.domain.Amount;
import com.klif.banking.domain.events.AccountOperationEvent;
import com.klif.banking.domain.events.DepositEvent;
import com.klif.banking.domain.events.WithdrawEvent;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import de.bwaldvogel.mongo.MongoServer;
import de.bwaldvogel.mongo.backend.memory.MemoryBackend;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.bson.Document;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MongoEventStreamTest {

  private MongoServer mongoServer;
  private MongoClient mongoClient;
  private MongoCollection<Document> mongoCollection;
  public static final DepositEvent DEPOSIT_EVENT = new DepositEvent(new Amount(50));
  public static final WithdrawEvent WITHDRAW_EVENT = new WithdrawEvent(new Amount(12));
  private MongoEventStream mongoEventStream;

  @BeforeEach
  void setUp() {
    mongoServer = new MongoServer(new MemoryBackend());
    mongoClient = new MongoClient(mongoServer.bind().getHostString(),
                                  mongoServer.bind().getPort());

    MongoDatabase database = mongoClient.getDatabase("account");
    mongoCollection = database.getCollection("AccountOperationEvents");
    mongoEventStream = new MongoEventStream(mongoClient);
  }

  @AfterEach
  void tearDown() {
    mongoServer.shutdown();
  }

  @Test
  void all_should_contains_events() {
    Document document1 = new Document("type", "deposit");
    document1.append("amount", 50);
    Document document2 = new Document("type", "withdraw");
    document2.append("amount", 12);
    mongoCollection.insertMany(asList(document1, document2));

    assertThat(mongoEventStream.all()).containsOnly(DEPOSIT_EVENT, WITHDRAW_EVENT);
  }

  @Test
  void publish_should_save_events() {
    mongoEventStream.publish(DEPOSIT_EVENT);
    mongoEventStream.publish(WITHDRAW_EVENT);

    assertThat(mongoEventStream.all()).containsOnly(DEPOSIT_EVENT, WITHDRAW_EVENT);
  }
}
