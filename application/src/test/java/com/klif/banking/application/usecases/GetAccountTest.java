package com.klif.banking.application.usecases;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import com.klif.banking.domain.Account;
import com.klif.banking.domain.Amount;
import com.klif.banking.domain.Balance;
import com.klif.banking.domain.StatementLine;
import com.klif.banking.domain.events.DepositEvent;
import com.klif.banking.domain.events.WithdrawEvent;
import com.klif.banking.domain.ports.EventStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetAccountTest {

  private static final DepositEvent DEPOSIT_20_EVENT = new DepositEvent(new Amount(20));
  public static final WithdrawEvent WITHDRAW_10_EVENT = new WithdrawEvent(new Amount(10));
  @Mock
  private EventStream eventStream;
  private GetAccount tested;

  @BeforeEach
  void setUp() {
    tested = new GetAccount(eventStream);
  }

  @Test
  void empty_account() {
    given(eventStream.all()).willReturn(emptyList());
    Account account = tested.account();
    assertThat(account.balance()).isEqualTo(new Balance(0));
    assertThat(account.statement()).isEmpty();
  }

  @Test
  void account_with_deposit_event() {
    given(eventStream.all()).willReturn(singletonList(DEPOSIT_20_EVENT));
    Account account = tested.account();
    assertThat(account.balance()).isEqualTo(new Balance(20));
    assertThat(account.statement()).containsOnly(new StatementLine(20, 20));
  }

  @Test
  void account_with_deposit_and_withdraw_event() {
    given(eventStream.all()).willReturn(asList(DEPOSIT_20_EVENT, WITHDRAW_10_EVENT));
    Account account = tested.account();
    assertThat(account.balance()).isEqualTo(new Balance(10));
    assertThat(account.statement()).contains(new StatementLine(20, 20),
                                             new StatementLine(-10, 10));
  }

}