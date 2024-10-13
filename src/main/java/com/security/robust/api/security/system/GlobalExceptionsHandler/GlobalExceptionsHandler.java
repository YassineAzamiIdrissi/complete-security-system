package com.security.robust.api.security.system.GlobalExceptionsHandler;

import com.security.robust.api.security.system.CustomExceptions.UserNotFoundException;
import com.security.robust.api.security.system.GlobalExceptionsHandler.ExceptionResponseDto.ExceptionResp;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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

}
