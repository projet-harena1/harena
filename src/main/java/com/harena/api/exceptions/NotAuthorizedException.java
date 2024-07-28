package com.harena.api.exceptions;

public class NotAuthorizedException extends ApiException {
    public NotAuthorizedException(String message) {
        super("NotAuthorizedException", message);
    }
}