package com.harena.api.exceptions;

public class ResourceNotFoundException extends ApiException {
  public ResourceNotFoundException(String message) {
    super("ResourceNotFoundException", message);
  }
}
