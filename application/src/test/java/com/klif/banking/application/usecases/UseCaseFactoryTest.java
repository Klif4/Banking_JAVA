package com.klif.banking.application.usecases;

import static org.assertj.core.api.Assertions.assertThat;

import com.klif.banking.domain.ports.EventStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UseCaseFactoryTest {

  @Mock
  private EventStream eventStream;

  private UseCaseFactory tested;

  @BeforeEach
  void setUp() {
    tested = new UseCaseFactory(eventStream);
  }

  @Test
  void createDepositToAccount() {
    assertThat(tested.createDepositToAccount()).isEqualTo(new DepositToAccount(eventStream));
  }

  @Test
  void createWithdrawToAccount() {
    assertThat(tested.createWithdrawToAccount()).isEqualTo(new WithdrawToAccount(eventStream));
  }
}