package com.dreamrealm.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginModel {
    @NotBlank
    private String username;

    private String email;

    @NotBlank
    private String password;

}