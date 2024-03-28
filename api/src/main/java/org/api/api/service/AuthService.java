package org.api.api.service;

import org.api.api.model.Auth;
import org.api.api.model.User;

import java.util.Optional;

import org.api.api.model.Auth;
import org.api.api.security.TokenResponse;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface AuthService {
    String registerUser(User user);
    TokenResponse loginUser(Auth auth);
}