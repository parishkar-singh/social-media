package org.api.api.service;

import org.api.api.model.Auth;
import org.api.api.model.User;

import org.api.api.security.TokenResponse;

public interface AuthService {
    String registerUser(User user);
    User getUserDetails(String accessToken);
    TokenResponse loginUser(Auth auth);
}