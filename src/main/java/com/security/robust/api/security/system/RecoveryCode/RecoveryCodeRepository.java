package com.security.robust.api.security.system.RecoveryCode;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecoveryCodeRepository extends JpaRepository<RecoveryCode, Integer> {
    Optional<RecoveryCode> findByRecoveryCode(String code);
}
