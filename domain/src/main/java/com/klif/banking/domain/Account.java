package com.klif.banking.domain;

public class Account {

  private final Balance balance = new Balance(0);
  private String statement = "Amount     Balance";

  public void deposit(Amount amount) {
    balance.add(amount.value());
    statement += "\n+" + amount.value() + "     " + balance.value();
  }

  public void withDraw(Amount amount) {
    balance.substract(amount.value());
    statement += "\n-" + amount.value() + "     " + balance.value();
  }

  public String printStatement() {
    return statement;
  }
}
