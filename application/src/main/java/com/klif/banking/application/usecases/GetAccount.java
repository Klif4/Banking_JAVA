package com.klif.banking.application.usecases;

import com.klif.banking.domain.Account;
import com.klif.banking.domain.AccountAggregate;
import com.klif.banking.domain.ports.EventStream;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GetAccount {

  private final EventStream eventStream;

  public Account account() {
    return AccountAggregate.builder().eventStream(eventStream).build().account();
  }

}
