package com.klif.banking.application.web;

import com.klif.banking.application.usecases.UseCaseFactory;
import com.klif.banking.domain.Amount;
import io.javalin.http.Context;
import java.util.Objects;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AccountResource {

  private final UseCaseFactory useCaseFactory;

  public void postDeposit(Context ctx) {
    Integer amount = ctx.formParam("amount", Integer.class).check(Objects::nonNull, "Missing amount").get();
    useCaseFactory.createDepositToAccount().deposit(new Amount(amount));
    ctx.status(204);
  }

  public void postWithdraw(Context ctx) {
    Integer amount = ctx.formParam("amount", Integer.class).check(Objects::nonNull, "Missing amount").get();
    useCaseFactory.createWithdrawToAccount().withdraw(new Amount(amount));
    ctx.status(204);
  }
}
