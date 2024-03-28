package org.api.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("auth")
public class Auth {
    @Id
    private String id;

    @Indexed(unique = true)
    private String username;

    private boolean active;
    private String password;
    private String accessToken;
    private long accessExpiration;

    private String refreshToken;
    private long refreshExpiration;

    // Other fields and methods as needed
}
