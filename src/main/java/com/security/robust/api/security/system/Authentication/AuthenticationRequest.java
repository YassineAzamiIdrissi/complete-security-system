package com.security.robust.api.security.system.Authentication;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AuthenticationRequest {
    @Email(message = "Invalid email format")
    @NotNull(message = "email has to be specifier")
    @NotEmpty(message = "email can't be empty")
    @NotBlank(message = "email can't be blank")
    private String email;
    @NotNull(message = "password has to be specifier")
    @NotEmpty(message = "password can't be empty")
    @NotBlank(message = "password can't be blank")
    @Size(min = 8, message = "Password must be have a minimum size of 8 characters")
    private String password;
}
