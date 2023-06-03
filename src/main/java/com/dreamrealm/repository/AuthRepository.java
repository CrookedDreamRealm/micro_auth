package com.dreamrealm.repository;

import com.dreamrealm.DTO.AuthDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface AuthRepository extends JpaRepository<AuthDTO, Integer> {
    @Query("SELECT a FROM auth a WHERE a.username = ?1")
    AuthDTO findByUsername(String username);

    @Query("SELECT a FROM auth a WHERE a.authId = ?1")
    AuthDTO findByUserId(UUID id);

    Optional<AuthDTO> findByEmail(String email);

    Boolean existsByEmail(String email);
}