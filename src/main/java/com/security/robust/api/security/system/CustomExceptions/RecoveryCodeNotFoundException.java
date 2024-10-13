package com.security.robust.api.security.system.CustomExceptions;

public class RecoveryCodeNotFoundException extends RuntimeException {
    public RecoveryCodeNotFoundException(String message) {
        super(message);
    }
}
