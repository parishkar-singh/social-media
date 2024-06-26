package org.api.api.controller;

import lombok.RequiredArgsConstructor;
import org.api.api.model.Auth;
import org.api.api.model.User;
import org.api.api.security.TokenResponse;
import org.api.api.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        try {
            String message = authService.registerUser(user);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to register user: " + e.getMessage());
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<?> loginUser(@RequestBody Auth auth) {
        try {
            TokenResponse tokenResponse = authService.loginUser(auth);
            if (tokenResponse != null) {
                return ResponseEntity.ok(tokenResponse);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Failed to authenticate");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to authenticate: " + e.getMessage());
        }
    }

    @GetMapping("/details")
    public ResponseEntity<?> getUserDetails(@RequestHeader("Authorization") String accessToken) {
        try {
            User userDetails = authService.getUserDetails(accessToken.replace("Bearer ", ""));
            if (userDetails != null) {
                return ResponseEntity.ok(userDetails);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid access token");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch user details: " + e.getMessage());
        }
    }
}
