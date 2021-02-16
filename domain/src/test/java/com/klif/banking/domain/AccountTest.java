package com.klif.banking.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class AccountTest {

  @Test
  void account_initialisation() {
    Account account = new Account();
    assertThat(account.printStatement()).isEqualTo("Amount     Balance");
  }

  @Test
  void deposit_one_time_should_add_one_line() {
    Account account = new Account();
    account.deposit(new Amount(100));
    assertThat(account.printStatement()).isEqualTo("Amount     Balance\n+100     100");
  }

  @Test
  void deposit_two_times_should_add_two_line() {
    Account account = new Account();
    account.deposit(new Amount(100));
    account.deposit(new Amount(75));
    assertThat(account.printStatement()).isEqualTo("Amount     Balance" + "\n+100     100" + "\n+75     175");
  }

  @Test
  void withdraw_should_substract_amount() {
    Account account = new Account();
    account.deposit(new Amount(100));
    account.deposit(new Amount(75));
    account.withDraw(new Amount(25));
    assertThat(account.printStatement()).isEqualTo("Amount     Balance"
                                                       + "\n+100     100"
                                                       + "\n+75     175"
                                                       + "\n-25     150"
    );
  }

  @Test
  void negative_value_on_account() {
    Account account = new Account();
    account.deposit(new Amount(100));
    account.deposit(new Amount(75));
    account.withDraw(new Amount(25));
    account.withDraw(new Amount(300));
    assertThat(account.printStatement()).isEqualTo("Amount     Balance"
                                                       + "\n+100     100"
                                                       + "\n+75     175"
                                                       + "\n-25     150"
                                                       + "\n-300     -150"
    );
  }
}