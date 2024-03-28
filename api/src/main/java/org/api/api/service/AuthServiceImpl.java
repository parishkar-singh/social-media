package org.api.api.service;

import lombok.RequiredArgsConstructor;
import org.api.api.model.Auth;
import org.api.api.model.User;
import org.api.api.repository.AuthRepository;
import org.api.api.repository.UserRepository;
import org.api.api.security.TokenResponse;
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
                Optional<Auth> optionalAuth = authRepository.findByUsername(user.getUsername());
                if (optionalAuth.isPresent()) {
                    Auth sessionAuth = optionalAuth.get();

                    long currentTime = Instant.now().getEpochSecond();
                    if (sessionAuth.getAccessExpiration() > currentTime) {
                        return generateTokenResponse(sessionAuth);
                    } else if (sessionAuth.getRefreshExpiration() > currentTime) {
                        String newAccessToken = generateToken();
                        long newAccessExpiration = Instant.now().plusSeconds(3600).getEpochSecond(); // Access token expires in 1 hour

                        sessionAuth.setAccessToken(newAccessToken);
                        sessionAuth.setAccessExpiration(newAccessExpiration);
                        authRepository.save(sessionAuth);

                        return generateTokenResponse(sessionAuth);
                    }
                }

                // No Auth record found or both tokens are expired
                return generateNewTokenResponse(user.getUsername());
            }
        }

        // Authentication failed
        return null; // Or handle authentication failure accordingly
    }

    private TokenResponse generateTokenResponse(Auth auth) {
        return TokenResponse.builder()
                .accessToken(auth.getAccessToken())
                .accessExpiration(auth.getAccessExpiration())
                .refreshToken(auth.getRefreshToken())
                .refreshExpiration(auth.getRefreshExpiration())
                .build();
    }

    private TokenResponse generateNewTokenResponse(String username) {
        String accessToken = generateToken();
        long accessExpiration = Instant.now().plusSeconds(3600).getEpochSecond(); // Access token expires in 1 hour

        String refreshToken = generateToken();
        long refreshExpiration = Instant.now().plusSeconds(604800).getEpochSecond(); // Refresh token expires in 7 days

        Auth sessionAuth = Auth.builder()
                .username(username)
                .accessToken(accessToken)
                .accessExpiration(accessExpiration)
                .refreshToken(refreshToken)
                .refreshExpiration(refreshExpiration)
                .build();

        authRepository.save(sessionAuth);

        return TokenResponse.builder()
                .accessToken(accessToken)
                .accessExpiration(accessExpiration)
                .refreshToken(refreshToken)
                .refreshExpiration(refreshExpiration)
                .build();
    }

    private String generateToken() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
