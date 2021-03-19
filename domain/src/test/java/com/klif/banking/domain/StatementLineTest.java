package com.klif.banking.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class StatementLineTest {
  @Test
  void should_return_positive_line() {
    assertThat(new StatementLine(12, 30).toString()).isEqualTo("\n+12     30");
  }

  @Test
  void should_return_negative_line() {
    assertThat(new StatementLine(-12, 30).toString()).isEqualTo("\n-12     30");
  }
}