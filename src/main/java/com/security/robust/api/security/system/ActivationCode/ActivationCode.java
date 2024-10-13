package com.security.robust.api.security.system.ActivationCode;

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
public class ActivationCode {
    @GeneratedValue
    @Id
    private Integer id;
    private String activationCode;
    private LocalDateTime createdAt;
    private LocalDateTime usedAt;
    private LocalDateTime expiresAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
