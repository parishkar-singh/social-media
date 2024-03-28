package org.api.api.controller;

import org.api.api.model.Auth;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import org.api.api.model.User;
import org.api.api.repository.UserRepository;
import org.api.api.repository.AuthRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final UserRepository userRepository;
    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    org.api.api.utils.Logger authControllerLogger = new org.api.api.utils.Logger("Auth Controller");

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        try {
            if (userRepository.existsByUsername(user.getUsername())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
            }

            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);

            userRepository.save(user);

            authControllerLogger.logSuccess("User registered");
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            authControllerLogger.logError("Failed to register");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to register user");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody Auth auth) {
        try {
            // Retrieve user by username from auth repository
            Auth storedAuth = authRepository.findByUsername(auth.getUsername())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));

            // Check if the provided password matches the stored password
            if (!passwordEncoder.matches(auth.getPassword(), storedAuth.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password");
            }

            // Authentication successful
            authControllerLogger.logSuccess("User logged in");
            return ResponseEntity.ok("User logged in successfully");
        } catch (IllegalArgumentException e) {
            authControllerLogger.logError("User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        } catch (Exception e) {
            authControllerLogger.logError("Failed to login");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to login");
        }
    }
}
