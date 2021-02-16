package com.klif.banking.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Balance {
  @NonNull
  private int value;

  public void add(int valueToAdd) {
    value += valueToAdd;
  }

  public int value() {
    return value;
  }

  public void substract(int valueToSubstract) {
    value -= valueToSubstract;
  }
}
