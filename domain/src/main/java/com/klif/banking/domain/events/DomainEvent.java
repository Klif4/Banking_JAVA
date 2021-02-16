package com.klif.banking.domain.events;

import com.klif.banking.domain.Amount;
import java.time.Instant;

public interface DomainEvent {
  public Amount amount();
}
