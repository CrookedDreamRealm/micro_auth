package com.dreamrealm.DTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity(name="auth")
public class AuthDTO {
    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private UUID userId;

    private String username;
    private String password;
    private String email;
}
