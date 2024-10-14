package com.security.robust.api.security.system.GlobalExceptionsHandler;

import com.security.robust.api.security.system.CustomExceptions.*;
import com.security.robust.api.security.system.GlobalExceptionsHandler.ExceptionResponseDto.ExceptionResp;
import jakarta.mail.MessagingException;
import lombok.Locked;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.http.HttpStatus.GONE;

@RestControllerAdvice
public class GlobalExceptionsHandler {
    @ExceptionHandler(UserNotFoundException.class)
    ResponseEntity<ExceptionResp> handleUserNotFoundException(UserNotFoundException exp) {
        ExceptionResp resp = new ExceptionResp();
        resp.setMessage(exp.getMessage());
        return ResponseEntity.status(404).
                body(resp);
    }

    @ExceptionHandler(MessagingException.class)
    ResponseEntity<ExceptionResp> handleMessagingException(MessagingException exp) {
        ExceptionResp resp = new ExceptionResp();
        resp.setMessage("Something went wrong in the mailing service inside the sever..");
        return ResponseEntity.status(500).
                body(resp);
    }

    @ExceptionHandler(ActivationCodeNotFoundException.class)
    ResponseEntity<ExceptionResp> handleActivationCodeNotFoundException
            (ActivationCodeNotFoundException exp) {
        ExceptionResp resp = new ExceptionResp();
        resp.setMessage("Something went wrong in the mailing service inside the sever..");
        return ResponseEntity.status(404).
                body(resp);
    }

    @ExceptionHandler(ActivationCodeExpiredException.class)
    ResponseEntity<ExceptionResp> handleActivationCodeExpiredException(
            ActivationCodeExpiredException exp) {
        ExceptionResp resp = new ExceptionResp();
        resp.setMessage(exp.getMessage());
        return ResponseEntity.status(GONE).
                body(resp);
    }

    @ExceptionHandler(AuthorityNotFoundException.class)
    ResponseEntity<ExceptionResp> handleAuthorityNotFoundException(
            AuthorityNotFoundException exp
    ) {
        ExceptionResp resp = new ExceptionResp();
        resp.setMessage(exp.getMessage());
        return ResponseEntity.status(404).
                body(resp);
    }

    @ExceptionHandler(RecoveryCodeNotFoundException.class)
    ResponseEntity<ExceptionResp> handleRecoveryCodeNotFoundException(
            RecoveryCodeNotFoundException exp
    ) {
        ExceptionResp resp = new ExceptionResp();
        resp.setMessage(exp.getMessage());
        return ResponseEntity.status(404).
                body(resp);
    }

    @ExceptionHandler(RecoveryCodeExpiredException.class)
    ResponseEntity<ExceptionResp> handleRecoveryCodeExpiredException(
            RecoveryCodeExpiredException exp
    ) {
        ExceptionResp resp = new ExceptionResp();
        resp.setMessage(exp.getMessage());
        return ResponseEntity.status(GONE).
                body(resp);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ExceptionResp> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exp
    ) {
        ExceptionResp resp = new ExceptionResp();
        Set<String> errors = new HashSet<>();
        exp.getBindingResult().getAllErrors().forEach(error -> {
            errors.add(error.getDefaultMessage());
        });
        resp.setErrors(errors);
        return ResponseEntity.status(400).
                body(resp);
    }

    @ExceptionHandler(DisabledException.class)
    ResponseEntity<ExceptionResp> handleDisabledException(DisabledException exp) {
        ExceptionResp resp = new ExceptionResp();
        resp.setMessage("Account is currently disabled");
        return ResponseEntity.status(403).
                body(resp);
    }

    @ExceptionHandler(LockedException.class)
    ResponseEntity<ExceptionResp> handleLockedException(LockedException exp) {
        ExceptionResp resp = new ExceptionResp();
        resp.setMessage("Account is currently locked, consider contacting an admin");
        return ResponseEntity.status(403).
                body(resp);
    }

    @ExceptionHandler(BadCredentialsException.class)
    ResponseEntity<ExceptionResp> handleBadCredentialsException(
            BadCredentialsException exp
    ) {
        ExceptionResp resp = new ExceptionResp();
        resp.setMessage("Email or password is incorrect..");
        return ResponseEntity.status(400).
                body(resp);
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<ExceptionResp> handleException(Exception exp) {
        ExceptionResp resp = new ExceptionResp();
        resp.setMessage(exp.getMessage());
        return ResponseEntity.status(500).
                body(resp);
    }
}
