package com.security.robust.api.security.system.Authentication;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmailRequest {
    @Email(message = "Invalid email format")
    @NotNull(message = "email has to be specifier")
    @NotEmpty(message = "email can't be empty")
    @NotBlank(message = "email can't be blank")
    private String email;
}
