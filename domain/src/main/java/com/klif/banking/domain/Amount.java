package com.klif.banking.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@AllArgsConstructor
@EqualsAndHashCode
public class Amount {
  private final int value;

  public int value() {
    return value;
  }
}
