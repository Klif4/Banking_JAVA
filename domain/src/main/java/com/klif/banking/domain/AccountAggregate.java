package com.klif.banking.domain;

import com.klif.banking.domain.events.DepositEvent;
import com.klif.banking.domain.events.AccountOperationEvent;
import com.klif.banking.domain.events.WithdrawEvent;
import com.klif.banking.domain.ports.EventStream;
import lombok.Builder;
import lombok.NonNull;


public class AccountAggregate {

  private final Account account = new Account();
  private final EventStream eventStream;

  @Builder
  public AccountAggregate(@NonNull EventStream eventStream) {
    this.eventStream = eventStream;
    eventStream.all().forEach(this::handle);
  }

  private void handle(AccountOperationEvent event) {
    if(event.getClass() == DepositEvent.class) {
      account.deposit(event.amount());
    }
    if(event.getClass() == WithdrawEvent.class) {
      account.withDraw(event.amount());
    }

  }

  public void depositOnAccount(Amount amount) {
    eventStream.publish(new DepositEvent(amount));
    account.deposit(amount);
  }

  public void withDrawFromAccount(Amount amount) {
    eventStream.publish(new WithdrawEvent(amount));
    account.withDraw(amount);
  }

  public Account account() {
    return account;
  }
}
