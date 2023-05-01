package com.dreamrealm.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
public class SignUpModel {
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private Set<String> role;

    private String username;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
}