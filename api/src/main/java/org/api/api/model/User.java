package org.api.api.model;

import lombok.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Data // getters, setters, toString, equals, and hashCode methods.
@NoArgsConstructor
@AllArgsConstructor
@Document //same as @Entity for JPA to use along with MySQL / PostgreSQL, etc.

public class User {

    @Id
    private String userId;


    @NotBlank(message = "Name is required")
    private String name;


    @NotBlank(message = "Username is required")
    @Indexed(unique = true)
    private String username;


    @NotBlank(message = "Email is required")
    @Indexed(unique = true)
    private String email;


    @NotBlank(message = "Password is required")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "Password must be at least 8 characters long and contain at least one letter and one number")
    private String password;

    private ArrayList<String> followers =new ArrayList<>();
    private ArrayList<String> following =new ArrayList<>();

    private ArrayList<String> blogIds = new ArrayList<>();

}
