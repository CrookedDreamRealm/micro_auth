package com.dreamrealm.controller;

import com.dreamrealm.DTO.AuthDTO;
import com.dreamrealm.DTO.RoleDTO;
import com.dreamrealm.Enum.ERole;
import com.dreamrealm.logic.AuthLogic;
import com.dreamrealm.model.*;
import com.dreamrealm.repository.AuthRepository;
import com.dreamrealm.repository.RoleRepository;
import com.dreamrealm.utils.JwtUtils;
import javax.validation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    AuthLogic authLogic;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    AuthRepository authRepository;

    @Autowired
    RoleRepository roleRepository;

    /*@RequestMapping(value = "createAccount", method = RequestMethod.POST)
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
    }*/
    @Autowired
    JwtUtils jwtUtils;
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginModel loginRequest) {
        System.out.println("test");
        if(loginRequest.getUsername() != null)
        {
            loginRequest.setEmail(loginRequest.getUsername());
        }
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);


        AuthModel authModel = (AuthModel) authentication.getPrincipal();
        List<String> roles = authModel.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponseModel(jwt,
                authModel.getUserId(),
                authModel.getUsername(),
                authModel.getEmail(),
                roles));
    }
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpModel signUpRequest) {
        if (authRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponseModel("Error: Username is already taken!"));
        }
        if (authRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponseModel("Error: Email is already in use!"));
        }
        // Create new user's account
        AuthDTO user = new AuthDTO(signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()), signUpRequest.getUsername());
        Set<String> strRoles = signUpRequest.getRole();
        Set<RoleDTO> roles = new HashSet<>();
        if (strRoles == null) {
            RoleDTO role = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(role);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "super":
                        RoleDTO role1 = roleRepository.findByName(ERole.ROLE_SUPER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(role1);
                        break;
                    case "moderator":
                        RoleDTO role2 = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(role2);
                        break;
                    case "admin":
                        RoleDTO role3 = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(role3);
                        break;
                    default:
                        RoleDTO role4 = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(role4);
                }
            });
        }
        user.setRoles(roles);
        authRepository.save(user);
        return ResponseEntity.ok(new MessageResponseModel("User registered successfully!"));
    }

    @GetMapping("/kube")
    public ResponseEntity<?> kube() {
        return ResponseEntity.ok(new MessageResponseModel("trashed"));
    }
}
