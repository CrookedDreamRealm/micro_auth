package com.dreamrealm.logic;

import com.dreamrealm.DTO.AuthDTO;
import com.dreamrealm.model.AuthModel;
import com.dreamrealm.repository.AuthRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthLogic {
    @Autowired
    AuthRepository authRepository;
    public Boolean createAccount(AuthModel auth){
        try{
            ModelMapper modelMapper = new ModelMapper();
            AuthDTO authDTO = modelMapper.map(auth, AuthDTO.class);
            authRepository.save(authDTO);
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }

    public AuthModel getAccount(AuthModel auth){
        AuthDTO authDTO = authRepository.findByUsername(auth.getUsername());
        ModelMapper modelMapper = new ModelMapper();
        auth = modelMapper.map(authDTO, AuthModel.class);
        return auth;
    }

    public Boolean removeAccount(UUID id){
        AuthDTO authDTO = authRepository.findByUserId(id);
        authRepository.delete(authDTO);
        return true;
    }
}
