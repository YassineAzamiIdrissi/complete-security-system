package com.security.robust.api.security.system.Authentication;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class NewPasswordCouple {
    @NotNull(message = "password has to be specifier")
    @NotEmpty(message = "password can't be empty")
    @NotBlank(message = "password can't be blank")
    @Size(min = 8, message = "Password must be have a minimum size of 8 characters")
    private String newPassword;
    private String confirmNewPassword;
}
