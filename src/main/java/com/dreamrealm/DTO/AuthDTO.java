package com.dreamrealm.DTO;

import jakarta.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity(name="auth")
public class AuthDTO {
    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    @Column(name = "auth_id")
    private UUID authId;

    @NotBlank
    @Size(max = 50)
    @Email
    private String username;
    @NotBlank
    @Size(max = 120)
    private String password;
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    private String password_reset_token;
    private String creation_of_token_date;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "auth_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private Set<RoleDTO> roles = new HashSet<>();
    public AuthDTO() {
    }
    public AuthDTO(String email, String password, String username) {
        this.email = email;
        this.password = password;
        this.username = username;
    }
}
