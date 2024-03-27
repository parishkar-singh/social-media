package org.api.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import org.api.api.model.Auth;
import org.api.api.repository.AuthRepository;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    org.api.api.utils.Logger authControllerLogger = new org.api.api.utils.Logger("Auth Controller");
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Auth user) {
        try {
            if (authRepository.findByUsername(user.getUsername()).isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            authRepository.save(user);
            authControllerLogger.logSuccess("User registered");
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            authControllerLogger.logError("Failed to register");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to register user");
        }
    }
}
