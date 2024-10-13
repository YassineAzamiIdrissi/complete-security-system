package com.security.robust.api.security.system.Dummy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CarResponse {
    private String car;
    private String ownerFullName;
    private Integer prodYear;
    private String description;
}
