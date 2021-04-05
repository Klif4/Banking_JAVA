package com.klif.banking.application.config;

public class ServerConfig {
  public int port = Integer.parseInt(System.getenv().get("PORT"));
}
