package com.klif.banking.domain;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class StatementTest {

  @Test
  void empty_statement() {
    Statement statement = new Statement();
    assertThat(statement.value()).isEqualTo(emptyList());
  }

  @Test
  void add_depositLine() {
    Statement statement = new Statement();
    statement.addDepositLine(new Amount(12), new Balance(12));
    assertThat(statement.value()).containsOnly(new StatementLine(12, 12));
  }

  @Test
  void add_withdrawLine() {
    Statement statement = new Statement();
    statement.addDepositLine(new Amount(12), new Balance(12));
    statement.addWithdrawLine(new Amount(10), new Balance(2));
    assertThat(statement.value()).containsExactly(new StatementLine(12, 12), new StatementLine(-10, 2));
  }
}
