package com.security.robust.api.security.system.ActivationCode;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ActivationCodeRepository extends JpaRepository<ActivationCode, Integer> {
    Optional<ActivationCode> findByActivationCode(String activationCode);
}
