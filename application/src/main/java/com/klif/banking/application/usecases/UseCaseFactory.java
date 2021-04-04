package com.klif.banking.application.usecases;

import com.klif.banking.domain.ports.EventStream;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UseCaseFactory {
  private final EventStream eventStream;

  public DepositToAccount createDepositToAccount() {
    return new DepositToAccount(eventStream);
  }

  public WithdrawToAccount createWithdrawToAccount() {
    return new WithdrawToAccount(eventStream);
  }

  public GetAccount createGetAccount() {
    return new GetAccount(eventStream);
  }

}
