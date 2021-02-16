package com.klif.banking.domain;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class Amount {
  @NonNull
  private final int value;

  public int value() {
    return value;
  }
}
