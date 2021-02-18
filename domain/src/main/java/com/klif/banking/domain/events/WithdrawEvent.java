package com.klif.banking.domain.events;

import com.klif.banking.domain.Amount;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@AllArgsConstructor
@EqualsAndHashCode
public class WithdrawEvent implements AccountOperationEvent {

  @NonNull
  private final Amount amount;

  @Override
  public Amount amount() {
    return amount;
  }
}
