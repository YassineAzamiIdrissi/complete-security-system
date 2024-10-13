package com.security.robust.api.security.system.CustomExceptions;

public class PasswordsMismatchException extends RuntimeException {
    public PasswordsMismatchException(String message) {
        super(message);
    }
}
