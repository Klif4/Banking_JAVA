package com.klif.banking.domain.events;

import com.klif.banking.domain.Amount;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class DepositEvent implements DomainEvent {

  @NonNull
  private final Amount amount;

  @Override
  public Amount amount() {
    return amount;
  }
}
