package com.security.robust.api.security.system.RecoveryCode;

import com.security.robust.api.security.system.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class RecoveryCode {
    @Id
    @GeneratedValue
    private Integer id;
    private String recoveryCode;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private boolean used;
    // Linking attributes :
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
