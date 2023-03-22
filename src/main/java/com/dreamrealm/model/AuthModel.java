package com.dreamrealm.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AuthModel {
    private UUID userId;

    private String username;
    private String password;
    private String email;
}
