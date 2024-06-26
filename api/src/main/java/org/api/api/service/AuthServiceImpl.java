package org.api.api.service;

import lombok.RequiredArgsConstructor;
import org.api.api.model.Auth;
import org.api.api.model.User;
import org.api.api.repository.AuthRepository;
import org.api.api.repository.UserRepository;
import org.api.api.security.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final AuthRepository authRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public String registerUser(User user) {
        try {
            if (userRepository.existsByUsername(user.getUsername())) {
                return "User already exists";
            }

            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);

            userRepository.save(user);

            return "User registered successfully";
        } catch (Exception e) {
            return "Failed to register user";
        }
    }

    @Override
    public TokenResponse loginUser(Auth auth) {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findByUsername(auth.getUsername()));

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (passwordEncoder.matches(auth.getPassword(), user.getPassword())) {
                return generateNewTokenResponse(user.getUsername());
            }
        }

        return null; // Authentication failed
    }

    private TokenResponse generateNewTokenResponse(String username) {
        Optional<Auth> optionalAuth = authRepository.findByUsername(username);

        Auth sessionAuth;
        if (optionalAuth.isPresent()) {
            sessionAuth = optionalAuth.get();
        } else {
            sessionAuth = new Auth();
            sessionAuth.setUsername(username);
        }

        String accessToken = generateToken();
        long accessExpiration = Instant.now().plusSeconds(3600).getEpochSecond(); // Access token expires in 1 hour

        String refreshToken = generateToken();
        long refreshExpiration = Instant.now().plusSeconds(604800).getEpochSecond(); // Refresh token expires in 7 days

        sessionAuth.setAccessToken(accessToken);
        sessionAuth.setAccessExpiration(accessExpiration);
        sessionAuth.setRefreshToken(refreshToken);
        sessionAuth.setRefreshExpiration(refreshExpiration);

        authRepository.save(sessionAuth);

        return TokenResponse.builder()
                .accessToken(accessToken)
                .build();
    }

    private String generateToken() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public User getUserDetails(String accessToken) {
        Auth auth = authRepository.findByAccessToken(accessToken);

        if (auth != null && auth.getAccessExpiration() > Instant.now().getEpochSecond()) {
            User userEntity = userRepository.findByUsername(auth.getUsername());
            if (userEntity != null) {
                return User.builder()
                        .userId(userEntity.getUserId())
                        .username(userEntity.getUsername())
                        .build();
            }
        }

        return null;
    }
}
