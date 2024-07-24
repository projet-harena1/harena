package com.harena.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class NotAuthorizedException extends ApiException {
  public NotAuthorizedException(String message) {
    super("NotAuthorizedException", message);
  }
}
