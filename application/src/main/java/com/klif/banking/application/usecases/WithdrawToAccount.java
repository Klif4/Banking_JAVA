package com.klif.banking.application.usecases;

import com.klif.banking.domain.AccountAggregate;
import com.klif.banking.domain.Amount;
import com.klif.banking.domain.ports.EventStream;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode
public class WithdrawToAccount {

  private final EventStream eventStream;

  public void withdraw(Amount amount) {
    AccountAggregate.builder().eventStream(eventStream).build().withDrawFromAccount(amount);
  }
}
