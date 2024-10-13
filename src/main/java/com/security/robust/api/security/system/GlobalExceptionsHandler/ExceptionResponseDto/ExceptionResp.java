package com.security.robust.api.security.system.GlobalExceptionsHandler.ExceptionResponseDto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionResp {
    private Set<String> errors;
    private String message;
}
