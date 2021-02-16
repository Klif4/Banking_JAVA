package com.klif.banking.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class BalanceTest {

  @Test
  void add_should_add_value_to_balance() {
    Balance balance = new Balance(0);
    balance.add(25);
    assertThat(balance).isEqualTo(new Balance(25));
  }

  @Test
  void add_twice_should_add_twice_value_to_balance() {
    Balance balance = new Balance(0);
    balance.add(25);
    balance.add(100);
    assertThat(balance).isEqualTo(new Balance(125));
  }

  @Test
  void substract_should_substract_value_to_balance() {
    Balance balance = new Balance(0);
    balance.add(25);
    balance.substract(25);
    assertThat(balance).isEqualTo(new Balance(0));
  }

  @Test
  void substract_twice_should_substract_twice_value_to_balance() {
    Balance balance = new Balance(0);
    balance.add(25);
    balance.substract(100);
    balance.substract(50);
    assertThat(balance).isEqualTo(new Balance(-125));
  }
}