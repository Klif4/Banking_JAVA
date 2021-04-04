package com.klif.banking.application.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import com.klif.banking.application.usecases.DepositToAccount;
import com.klif.banking.application.usecases.UseCaseFactory;
import com.klif.banking.application.usecases.WithdrawToAccount;
import com.klif.banking.domain.Amount;
import io.javalin.Javalin;
import io.restassured.RestAssured;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AccountResourceTest {

  @Mock
  private UseCaseFactory useCaseFactory;
  @Mock
  private DepositToAccount depositToAccount;
  @Mock
  private WithdrawToAccount withdrawToAccount;
  private Javalin app;

  @BeforeEach
  void setUp() {
    AccountResource tested = new AccountResource(useCaseFactory);
    app = Javalin.create()
        .post("/deposit", tested::postDeposit)
        .post("/withdraw", tested::postWithdraw)
        .start(1234);
  }

  @AfterEach
  void tearDown() {
    app.stop();
  }

  @Test
  void postDeposit_should_handle_deposit() {

    given(useCaseFactory.createDepositToAccount()).willReturn(depositToAccount);

    Map<String, Integer> body = new HashMap<>();
    body.put("amount", 20);
    
    RestAssured.given()
        .contentType("application/json")
        .body(body)
        .post("http://localhost:1234/deposit")
        .then()
        .statusCode(204);

    then(depositToAccount).should().deposit(new Amount(20));
  }

  @Test
  void postDeposit_should_400() {
    Map<String, Integer> body = new HashMap<>();
    body.put("wrong_parameter", 20);

    RestAssured.given()
        .contentType("application/json")
        .post("http://localhost:1234/deposit")
        .then()
        .statusCode(400);

  }

  @Test
  void postWithdraw_should_handle_deposit() {

    given(useCaseFactory.createWithdrawToAccount()).willReturn(withdrawToAccount);

    Map<String, Integer> body = new HashMap<>();
    body.put("amount", 20);

    RestAssured.given()
        .contentType("application/json")
        .body(body)
        .post("http://localhost:1234/withdraw")
        .then()
        .statusCode(204);

    then(withdrawToAccount).should().withdraw(new Amount(20));
  }

  @Test
  void postWithdraw_should_400() {
    Map<String, Integer> body = new HashMap<>();
    body.put("wrong_parameter", 20);

    RestAssured.given()
        .contentType("application/json")
        .body(body)
        .post("http://localhost:1234/withdraw")
        .then()
        .statusCode(400);

  }
}