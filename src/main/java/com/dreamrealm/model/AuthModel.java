package com.dreamrealm.model;

import com.dreamrealm.DTO.AuthDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
public class AuthModel implements UserDetails {
    private UUID userId;

    private String username;
    @JsonIgnore
    private String password;
    private String email;
    private String role;
    private Collection<? extends GrantedAuthority> authorities;
    public AuthModel(UUID userId, String email, String password, String username,
                           Collection<? extends GrantedAuthority> authorities) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.username = username;
        this.authorities = authorities;
    }
    public static AuthModel build(AuthDTO user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());
        return new AuthModel(
                user.getAuthId(),
                user.getEmail(),
                user.getPassword(),
                user.getUsername(),
                authorities);
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
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        AuthModel user = (AuthModel) o;
        return Objects.equals(userId, user.userId);
    }
}
