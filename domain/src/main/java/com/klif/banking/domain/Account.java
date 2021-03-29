package com.klif.banking.domain;

import java.util.ArrayList;
import java.util.List;

public class Account {

  private final Balance balance = new Balance(0);
  private final List<StatementLine> statement = new ArrayList<>();

  public void deposit(Amount amount) {
    balance.add(amount.value());
    statement.add(new StatementLine(amount.value(), balance.value()));
  }

  public List<StatementLine> statement() {
    return statement;
  }

  public void withDraw(Amount amount) {
    balance.substract(amount.value());
    statement.add(new StatementLine(-amount.value(), balance.value()));

  }

  public Balance balance() {
    return balance;
  }
}
