package com.security.robust.api.security.system.CustomExceptions;

public class RecoveryCodeExpiredException extends RuntimeException {
    public RecoveryCodeExpiredException(String message) {
        super(message);
    }
}
