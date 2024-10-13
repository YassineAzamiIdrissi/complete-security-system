package com.security.robust.api.security.system.Authentication;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@Tag(name = "auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authService;

    @PostMapping("register")
    ResponseEntity<?> register(@Valid @RequestBody RegistrationRequest request)
            throws MessagingException {
        authService.registerUser(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("authenticate")
    ResponseEntity<AuthenticationResponse> authenticate(
            @Valid @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok().body
                (authService.authenticate(request));
    }

    @GetMapping("activate-account")
    ResponseEntity<?> activateAccount(@RequestParam(name = "activation_code")
                                      String activationCode) throws MessagingException {
        authService.activateAccount(activationCode);
        return ResponseEntity.ok().
                build();
    }

    @PostMapping("demand-recovery")
    ResponseEntity<?> demandRecovery
            (@RequestParam(name = "email") String email)
            throws MessagingException {
        authService.demandRecovery(email);
        return ResponseEntity.ok().
                build();
    }

    @GetMapping("check-recovery")
    ResponseEntity<?> checkRecovery(
            @RequestParam(name = "recovery") String recovery
    ) throws MessagingException {
        authService.checkCodeValidity(recovery);
        return ResponseEntity.ok().
                build();
    }

    @PatchMapping("set-new-password")
    ResponseEntity<?> setNewPassword
            (@Valid @RequestBody NewPasswordCouple couple,
             @RequestParam(name = "code") String code) {
        authService.setNewPassword(couple, code);
        return ResponseEntity.ok().build();
    }
}
