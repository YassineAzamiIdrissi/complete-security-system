package com.security.robust.api.security.system.CustomExceptions;

public class ActivationCodeNotFoundException extends RuntimeException {
    public ActivationCodeNotFoundException(String message) {
        super(message);
    }
}
