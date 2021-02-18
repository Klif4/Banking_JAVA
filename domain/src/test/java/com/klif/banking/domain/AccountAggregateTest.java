package com.klif.banking.domain;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import com.klif.banking.domain.events.DepositEvent;
import com.klif.banking.domain.events.WithdrawEvent;
import com.klif.banking.domain.ports.EventStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AccountAggregateTest {

  @Mock
  private EventStream eventStream;

  @Test
  void should_return_empty_bill_when_there_is_no_event() {
    given(eventStream.all()).willReturn(emptyList());

    AccountAggregate aggregate = AccountAggregate.builder()
        .eventStream(eventStream)
        .build();

    assertThat(aggregate.statement()).isEqualTo("Amount     Balance");
  }

  @Test
  void should_handle_deposit_events() {
    given(eventStream.all()).willReturn(singletonList(new DepositEvent(new Amount(25))));

    AccountAggregate aggregate = AccountAggregate.builder()
        .eventStream(eventStream)
        .build();

    assertThat(aggregate.statement()).isEqualTo("Amount     Balance"
                                                    + "\n+" + 25 + "     " + "25"
    );
  }

  @Test
  void should_handle_withdraw_events() {
    given(eventStream.all()).willReturn(asList(new DepositEvent(new Amount(25)), new WithdrawEvent(new Amount(12))));

    AccountAggregate aggregate = AccountAggregate.builder()
        .eventStream(eventStream)
        .build();

    assertThat(aggregate.statement()).isEqualTo("Amount     Balance"
                                                    + "\n+" + 25 + "     " + 25
                                                    + "\n-" + 12 + "     " + 13
    );
  }

  @Test
  void should_publish_deposit_event_on_deposit() {
    // Given
    given(eventStream.all()).willReturn(emptyList());

    AccountAggregate aggregate = AccountAggregate.builder()
        .eventStream(eventStream)
        .build();

    Amount amount = new Amount(25);

    // When
    aggregate.depositOnAccount(amount);


    // Then
    then(eventStream).should().publish(new DepositEvent(amount));
    assertThat(aggregate.statement()).isEqualTo("Amount     Balance" + "\n+" + 25 + "     " + 25);
  }

  @Test
  void should_publish_withdraw_event_on_withdraw() {
    // Given
    given(eventStream.all()).willReturn(emptyList());

    AccountAggregate aggregate = AccountAggregate.builder()
        .eventStream(eventStream)
        .build();

    // When
    Amount amount = new Amount(25);
    aggregate.withDrawFromAccount(amount);


    // Then
    then(eventStream).should().publish(new WithdrawEvent(amount));
    assertThat(aggregate.statement()).isEqualTo("Amount     Balance" + "\n-" + 25 + "     " + -25);
  }
}