package com.klif.banking.application.usecases;

import com.klif.banking.domain.AccountAggregate;
import com.klif.banking.domain.Amount;
import com.klif.banking.domain.ports.EventStream;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode
public class DepositToAccount {

  private final EventStream eventStream;

  public void deposit(Amount amount) {
    AccountAggregate.builder().eventStream(eventStream).build().depositOnAccount(amount);
  }

}
