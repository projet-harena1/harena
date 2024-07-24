package com.harena.api.exceptions;

import lombok.Getter;

public class ApiException extends RuntimeException {
  @Getter private final String type;
  private final String message;

  public ApiException(String type, String message) {
    super(message);
    this.type = type;
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
