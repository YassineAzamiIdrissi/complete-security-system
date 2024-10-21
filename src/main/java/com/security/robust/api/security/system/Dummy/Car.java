package com.security.robust.api.security.system.Dummy;

import com.security.robust.api.security.system.BaseEntity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class Car extends BaseEntity {
    @GeneratedValue
    @Id
    private Integer id;
    private String name;
    private String description;
    private Integer prodYear;
    private String ownerId;
}
