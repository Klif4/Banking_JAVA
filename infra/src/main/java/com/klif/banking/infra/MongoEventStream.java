package com.klif.banking.infra;

import com.klif.banking.domain.Amount;
import com.klif.banking.domain.events.AccountOperationEvent;
import com.klif.banking.domain.events.DepositEvent;
import com.klif.banking.domain.events.WithdrawEvent;
import com.klif.banking.domain.ports.EventStream;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

public class MongoEventStream implements EventStream {


  private final MongoCollection<Document> mongoCollection;

  public MongoEventStream(MongoClient mongoClient) {
    mongoCollection = mongoClient.getDatabase("account").getCollection("AccountOperationEvents");
  }

  @Override
  public List<AccountOperationEvent> all() {
    List<AccountOperationEvent> target = new ArrayList<>();
    mongoCollection.find().map(this::map).into(target);
    return target;
  }

  private AccountOperationEvent map(Document document) {
    Amount amount = new Amount(document.getInteger("amount"));
    if (document.getString("type").equals("deposit")) {
      return new DepositEvent(amount);
    }
    return  new WithdrawEvent(amount);
  }

  @Override
  public void publish(AccountOperationEvent event) {
    Document eventDocument = new Document();
    if (event.getClass() == DepositEvent.class) {
      eventDocument.append("type", "deposit");
    }
    if (event.getClass() == WithdrawEvent.class) {
      eventDocument.append("type", "withdraw");
    }
    eventDocument.append("amount", event.amount().value());
    mongoCollection.insertOne(eventDocument);
  }
}
