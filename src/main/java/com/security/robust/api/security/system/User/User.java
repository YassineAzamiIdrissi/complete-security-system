package com.security.robust.api.security.system.User;

import com.security.robust.api.security.system.ActivationCode.ActivationCode;
import com.security.robust.api.security.system.Authority.Authority;
import com.security.robust.api.security.system.Dummy.Car;
import com.security.robust.api.security.system.RecoveryCode.RecoveryCode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "__user")
@Builder
@Data
@EntityListeners(AuditingEntityListener.class)
public class User implements Principal, UserDetails {
    @Id
    @GeneratedValue
    private Integer id;
    private String firstname;
    private String lastname;
    @Column(unique = true)
    private String email;
    private String password;
    private boolean locked;
    private boolean enabled;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_auths",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "auth_id")
    )
    private List<Authority> auths;

    @OneToMany(fetch = FetchType.EAGER,
            mappedBy = "user",
            cascade = CascadeType.ALL)
    private List<ActivationCode> activationCodes;

    @OneToMany(fetch = FetchType.EAGER,
            mappedBy = "user",
            cascade = CascadeType.ALL)
    private List<RecoveryCode> recoveryCodes;

    @OneToMany(mappedBy = "owner")
    private List<Car> cars;

    @Override
    public String getName() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return auths.stream().map(
                auth -> new SimpleGrantedAuthority(auth.getAuthority())
        ).collect(Collectors.toList());
    }


    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }


    public String generateFullName() {
        return firstname + " " + lastname;
    }
}
