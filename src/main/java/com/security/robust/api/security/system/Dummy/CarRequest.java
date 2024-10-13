package com.security.robust.api.security.system.Dummy;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
public class CarRequest {
    private Integer id;
    @NotNull(message = "car name has to be specified")
    @NotEmpty(message = "car name can't be empty")
    @NotBlank(message = "car name can't be blank")
    private String name;
    @NotNull(message = "car description has to be specified")
    @NotEmpty(message = "car description can't be empty")
    @NotBlank(message = "car description can't be blank")
    private String description;
    @Positive(message = "year can't be less than 0")
    @Min(value = 1900, message = "year can't be less than 1900")
    private Integer year;
}
