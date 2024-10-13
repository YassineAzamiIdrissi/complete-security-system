package com.security.robust.api.security.system.GlobalExceptionsHandler;

import com.security.robust.api.security.system.CustomExceptions.*;
import com.security.robust.api.security.system.GlobalExceptionsHandler.ExceptionResponseDto.ExceptionResp;
import jakarta.mail.MessagingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
}
