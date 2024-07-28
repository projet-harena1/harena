package com.harena.api.exceptions.handlers;

import com.harena.api.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public AppError handleBadRequestException(BadRequestException e) {
        return new AppError(e.getType(), e.getMessage());
    }

    @ExceptionHandler(NotAuthorizedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public AppError handleNotAuthorizedException(NotAuthorizedException e) {
        return new AppError(e.getType(), e.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public AppError handleResourceNotFoundException(ResourceNotFoundException e) {
        return new AppError(e.getType(), e.getMessage());
    }

    @ExceptionHandler(TooManyRequestsException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
    public AppError handleTooManyRequestsException(TooManyRequestsException e) {
        return new AppError(e.getType(), e.getMessage());
    }

    @ExceptionHandler(InternalServerException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public AppError handleInternalServerException(InternalServerException e) {
        return new AppError(e.getType(), e.getMessage());
    }
}
