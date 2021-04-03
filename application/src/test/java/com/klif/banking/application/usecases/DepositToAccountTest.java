package com.klif.banking.application.usecases;

import static org.mockito.BDDMockito.then;

import com.klif.banking.domain.Amount;
import com.klif.banking.domain.events.DepositEvent;
import com.klif.banking.domain.ports.EventStream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DepositToAccountTest {

  @Mock
  private EventStream eventStream;

  @Test
  void deposit_should_save_deposit_event() {
    Amount amount = new Amount(20);
    new DepositToAccount(eventStream).deposit(amount);
    then(eventStream).should().publish(new DepositEvent(amount));
  }
}