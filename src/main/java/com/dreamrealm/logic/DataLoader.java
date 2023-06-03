package com.dreamrealm.logic;

import com.dreamrealm.DTO.RoleDTO;
import com.dreamrealm.Enum.ERole;
import com.dreamrealm.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {
    @Autowired
    private RoleRepository roleRepository;

    public void run(ApplicationArguments args) {
        if(roleRepository.findTableEmpty() <= 0)
        {
            roleRepository.save(new RoleDTO(ERole.ROLE_SUPER));
            roleRepository.save(new RoleDTO(ERole.ROLE_MODERATOR));
            roleRepository.save(new RoleDTO(ERole.ROLE_USER));
            roleRepository.save(new RoleDTO(ERole.ROLE_ADMIN));
        }
    }
}
