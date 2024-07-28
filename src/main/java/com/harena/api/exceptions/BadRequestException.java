package com.harena.api.exceptions;

public class BadRequestException extends ApiException {
    public BadRequestException(String message) {
        super("BadRequestException", message);
    }
}