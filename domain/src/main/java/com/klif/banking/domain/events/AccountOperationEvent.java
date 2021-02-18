package com.klif.banking.domain.events;

import com.klif.banking.domain.Amount;

public interface AccountOperationEvent {
  Amount amount();
}
