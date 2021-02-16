package com.klif.banking.domain.ports;

import com.klif.banking.domain.events.DomainEvent;
import java.util.List;

public interface EventStore {
  List<DomainEvent> all();
}
