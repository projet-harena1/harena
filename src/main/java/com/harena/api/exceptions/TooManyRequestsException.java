package com.harena.api.exceptions;

public class TooManyRequestsException extends ApiException {
    public TooManyRequestsException(String message) {
        super("TooManyRequestsException", message);
    }
}