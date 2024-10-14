package com.security.robust.api.security.system.Authentication;

import com.security.robust.api.security.system.ActivationCode.ActivationCode;
import com.security.robust.api.security.system.ActivationCode.ActivationCodeRepository;
import com.security.robust.api.security.system.Authority.Authority;
import com.security.robust.api.security.system.Authority.AuthorityRepository;
import com.security.robust.api.security.system.CustomExceptions.*;
import com.security.robust.api.security.system.Email.EmailService;
import com.security.robust.api.security.system.RecoveryCode.RecoveryCode;
import com.security.robust.api.security.system.RecoveryCode.RecoveryCodeRepository;
import com.security.robust.api.security.system.Security.JwtService;
import com.security.robust.api.security.system.User.User;
import com.security.robust.api.security.system.User.UserRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.security.robust.api.security.system.Email.EmailTemplate.ACTIVATE_ACCOUNT;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepo;
    private final AuthorityRepository authRepo;
    private final RecoveryCodeRepository recoveryRepo;
    private final ActivationCodeRepository activationCodeRepo;


    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;
    private final EmailService emailService;

    @Value("${application.links.recovery}")
    private String recoveryLink;
    @Value("${application.links.activation}")
    private String activationLink;


    public void setNewPassword(NewPasswordCouple couple, String code) {
        RecoveryCode savedCode = recoveryRepo.findByRecoveryCode
                (code).orElseThrow
                (() -> new RecoveryCodeNotFoundException
                        ("Recovery code " + code + " is incorrect"));
        if (!savedCode.isUsed()) {
            throw new RuntimeException("This code isn't validated by the user yet");
        }
        User concernedUser = savedCode.getUser();
        if (!Objects.equals(couple.getNewPassword(), couple.getConfirmNewPassword())) {
            throw new PasswordsMismatchException
                    ("Passwords do not match");
        }
        concernedUser.setPassword(passwordEncoder.encode(couple.getNewPassword()));
        userRepo.save(concernedUser);
    }

    public void checkCodeValidity(String code)
            throws MessagingException {
        RecoveryCode recoveryCode = recoveryRepo.findByRecoveryCode(code).
                orElseThrow(() -> new
                        RecoveryCodeNotFoundException("Code " + code + " isn't valid"));
        if (recoveryCode.getExpiresAt().isBefore(LocalDateTime.now())) {
            demandRecovery(recoveryCode.getUser().getEmail());
            throw new RecoveryCodeExpiredException
                    ("Code " + code + " expired a new one has been already sent");
        }
        recoveryCode.setUsed(true);
        recoveryRepo.save(recoveryCode);
    }

    public void demandRecovery(String email)
            throws MessagingException {
        User concernedUser = userRepo.findByEmail(email).
                orElseThrow(() -> new UserNotFoundException
                        ("Email " + email + " doesn't concern any existing user..."));
        String recoveryCode = generateAndSaveRecoveryCode(concernedUser);
        emailService.sendEmail(
                concernedUser.getEmail(),
                concernedUser.generateFullName(),
                null,
                recoveryCode,
                recoveryLink,
                "Recover your password"
        );
    }

    public AuthenticationResponse authenticate(
            AuthenticationRequest authRequest
    ) {
        UsernamePasswordAuthenticationToken authenticationToken = new
                UsernamePasswordAuthenticationToken(
                authRequest.getEmail(),
                authRequest.getPassword()
        );
        var authentication = authenticationManager.authenticate(authenticationToken);
        User user = (User) authentication.getPrincipal();
        Map<String, Object> claims = new HashMap<>();
        claims.put("fullName", user.generateFullName());
        String accessToken = jwtService.generateToken(
                user, claims
        );
        return AuthenticationResponse.builder().
                accessToken(accessToken).
                build();
    }

    public void registerUser(RegistrationRequest request)
            throws MessagingException {
        Authority userAuthority = authRepo.findByAuthority("USER").
                orElseThrow(() -> new
                        AuthorityNotFoundException
                        ("Authorities aren't initialized yet.."));
        User newUser = User.builder().
                firstname(request.getFirstname()).
                lastname(request.getLastname()).
                email(request.getEmail()).
                password(passwordEncoder.encode(request.getPassword())).
                locked(false).
                enabled(false).
                auths(List.of(userAuthority)).
                build();
        userRepo.save(newUser);
        sendEmail(newUser);
    }

    public void activateAccount(String activationCode)
            throws MessagingException {
        ActivationCode concernedCode =
                activationCodeRepo.findByActivationCode(activationCode).
                        orElseThrow(() -> new ActivationCodeNotFoundException
                                ("Activation code " + activationCode + " is incorrect !"));
        if (concernedCode.getExpiresAt().isBefore(LocalDateTime.now())) {
            sendEmail(concernedCode.getUser());
            throw new ActivationCodeExpiredException("Activation code " + activationCode +
                    " expired a new one has been already sent");
        }
        User user = userRepo.findById(
                concernedCode.getUser().getId()
        ).orElseThrow(() -> new UserNotFoundException("User not found"));
        concernedCode.setUsedAt(LocalDateTime.now());
        user.setEnabled(true);
        userRepo.save(user);
        activationCodeRepo.save(concernedCode);
    }

    private void sendEmail(User user)
            throws MessagingException {
        String code = generateAndSaveActivationCode(user);
        emailService.sendEmail(
                user.getEmail(),
                user.generateFullName(),
                ACTIVATE_ACCOUNT,
                code,
                activationLink,
                "Account activation"
        );
    }

    private String generateAndSaveRecoveryCode(User user) {
        String recovery = generateCode(10);
        RecoveryCode recoveryCode = RecoveryCode.
                builder().
                createdAt(LocalDateTime.now()).
                expiresAt(LocalDateTime.now().plusMinutes(15)).
                recoveryCode(recovery).
                user(user).
                build();
        recoveryRepo.save(recoveryCode);
        return recovery;
    }

    private String generateAndSaveActivationCode(User user) {
        String code = generateCode(6);
        ActivationCode savedCode =
                ActivationCode.builder().
                        activationCode(code).
                        createdAt(LocalDateTime.now()).
                        expiresAt(LocalDateTime.now().plusMinutes(15)).
                        user(user).
                        build();
        activationCodeRepo.save(savedCode);
        return code;
    }

    private String generateCode(int length) {
        String nums = "0123456789";
        StringBuilder sb = new StringBuilder();
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < length; i++) {
            int randIndex = random.nextInt(nums.length());
            sb.append(nums.charAt(randIndex));
        }
        return sb.toString();
    }
}
