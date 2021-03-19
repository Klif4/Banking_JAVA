package com.klif.banking.domain;

import java.util.ArrayList;
import java.util.List;

public class Statement {

  private final List<StatementLine> statementLines = new ArrayList<>();

  @Override
  public String toString() {
    StringBuilder result = new StringBuilder("Amount     Balance");
    for (StatementLine statementLine : statementLines) {
      result.append(statementLine.toString());
    }
    return result.toString();
  }

  public void addDepositLine(Amount amount, Balance balance) {
    statementLines.add(new StatementLine(amount.value(), balance.value()));
  }

  public List<StatementLine> value() {
    return statementLines;
  }

  public void addWithdrawLine(Amount amount, Balance balance) {
    statementLines.add(new StatementLine(-amount.value(), balance.value()));
  }
}
