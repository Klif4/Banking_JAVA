package com.klif.banking.application.assembly;

import com.klif.banking.application.usecases.DepositToAccount;
import com.klif.banking.application.usecases.UseCaseFactory;
import com.klif.banking.application.usecases.WithdrawToAccount;
import com.klif.banking.application.web.AccountResource;
import com.klif.banking.domain.ports.EventStream;
import com.klif.banking.infra.MongoEventStream;
import com.mongodb.MongoClient;
import io.javalin.Javalin;

public class Assembler {

  private AccountResource accountResource;

  public void start () {
    MongoClient mongoClient = new MongoClient("localhost");
    EventStream eventStream = new MongoEventStream(mongoClient);
    UseCaseFactory useCaseFactory = new UseCaseFactory(eventStream);
    accountResource = new AccountResource(useCaseFactory);

    Javalin app = Javalin.create().start(3000);
    endpoints(app);
  }

  public void endpoints(Javalin app) {
    app.post("/deposit", accountResource::postDeposit);
    app.post("/withdraw", accountResource::postWithdraw);
  }

}
