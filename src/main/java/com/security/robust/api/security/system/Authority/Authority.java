package com.security.robust.api.security.system.Authority;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.security.robust.api.security.system.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Authority {
    @Id
    @GeneratedValue
    private Integer id;
    private String authority;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt;

    // linking attributes :
    @ManyToMany(mappedBy = "auths")
    @JsonIgnore
    private List<User> users;
}
