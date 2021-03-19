package com.klif.banking.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@AllArgsConstructor
@EqualsAndHashCode
public class StatementLine {
  private final int amount;
  private final int balance;

  @Override
  public String toString() {
    String sign = amount > 0 ? "+" : "";
    return "\n" + sign + amount + "     " + balance;
  }
}
