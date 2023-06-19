package com.dreamrealm.DTO;

import com.dreamrealm.Enum.ERole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "roles")
public class RoleDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roles_id")
    private Integer rolesId;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;
    public RoleDTO() {
    }
    public RoleDTO(ERole name) {
        this.name = name;
    }
}
