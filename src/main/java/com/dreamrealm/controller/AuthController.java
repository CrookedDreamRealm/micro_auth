package com.dreamrealm.controller;

import com.dreamrealm.logic.AuthLogic;
import com.dreamrealm.model.AuthModel;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    AuthLogic authLogic;

    @RequestMapping(value = "createAccount", method = RequestMethod.POST)
    public ResponseEntity<?> createAccount(@Valid @RequestBody AuthModel auth){
        Boolean response = authLogic.createAccount(auth);
        return ResponseEntity.ok()
                .body(response);
    }

    @RequestMapping(value = "getAccount", method = RequestMethod.GET)
    public ResponseEntity<?> getAccount(@Valid @RequestBody AuthModel auth){
        AuthModel response = authLogic.getAccount(auth);
        return ResponseEntity.ok()
                .body(response);
    }
}
