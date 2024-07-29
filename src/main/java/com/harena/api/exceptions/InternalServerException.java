package com.harena.api.exceptions;

public class InternalServerException extends ApiException {
  public InternalServerException(String message) {
    super("InternalServerException", message);
  }
}
