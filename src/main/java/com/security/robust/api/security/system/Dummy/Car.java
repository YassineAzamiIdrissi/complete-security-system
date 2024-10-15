package com.security.robust.api.security.system.Dummy;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Car {
    @GeneratedValue
    @Id
    private Integer id;
    private String name;
    private String description;
    private Integer prodYear;

    @CreatedBy
    @Column(nullable = false, updatable = false)
    private String createdBy;
    @LastModifiedBy
    @Column(insertable = false)
    private String lastModifiedBy;

}
