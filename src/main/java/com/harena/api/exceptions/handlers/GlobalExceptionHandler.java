package com.harena.api.exceptions.handlers;

import com.harena.api.exceptions.*;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(BadRequestException.class)
  @ResponseBody
  public ApiException handleBadRequestException(BadRequestException e) {
    return e;
  }

  @ExceptionHandler(NotAuthorizedException.class)
  @ResponseBody
  public ApiException handleNotAuthorizedException(NotAuthorizedException e) {
    return e;
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  @ResponseBody
  public ApiException handleResourceNotFoundException(ResourceNotFoundException e) {
    return e;
  }

  @ExceptionHandler(TooManyRequestsException.class)
  @ResponseBody
  public ApiException handleTooManyRequestsException(TooManyRequestsException e) {
    return e;
  }

  @ExceptionHandler(InternalServerException.class)
  @ResponseBody
  public ApiException handleInternalServerException(InternalServerException e) {
    return e;
  }
}
