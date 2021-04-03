package com.klif.banking.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@AllArgsConstructor
@EqualsAndHashCode
@Data
public class Amount {
  private final int value;

  public int value() {
    return value;
  }
}
