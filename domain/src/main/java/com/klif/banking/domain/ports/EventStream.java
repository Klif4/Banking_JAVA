package com.klif.banking.domain.ports;

import com.klif.banking.domain.events.AccountOperationEvent;
import java.util.List;

public interface EventStream {

  List<AccountOperationEvent> all();

  void publish(AccountOperationEvent event);
}
