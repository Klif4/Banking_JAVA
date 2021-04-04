package com.klif.banking.application.web;

import com.klif.banking.application.usecases.UseCaseFactory;
import com.klif.banking.domain.Amount;
import io.javalin.http.Context;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public class AccountResource {

  private final UseCaseFactory useCaseFactory;

  public void postDeposit(Context ctx) {
    PostBody postBody = ctx.bodyValidator(PostBody.class).check(Objects::nonNull).get();
    useCaseFactory.createDepositToAccount().deposit(new Amount(postBody.amount));
    ctx.status(204);
  }

  public void postWithdraw(Context ctx) {
    PostBody postBody = ctx.bodyValidator(PostBody.class).check(Objects::nonNull).get();
    useCaseFactory.createWithdrawToAccount().withdraw(new Amount(postBody.amount));
    ctx.status(204);
  }

  @Data
  private static class PostBody {
    private int amount;
  }
}
