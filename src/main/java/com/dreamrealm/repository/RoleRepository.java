package com.dreamrealm.repository;

import com.dreamrealm.DTO.RoleDTO;
import com.dreamrealm.Enum.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleDTO, Long> {
    @Query("SELECT count(*) FROM auth a")
    Integer findTableEmpty();
    Optional<RoleDTO> findByName(ERole name);
}