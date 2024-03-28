package org.api.api.model;

import lombok.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collection;

@Data // getters, setters, toString, equals, and hashCode methods.
@NoArgsConstructor
@AllArgsConstructor
@Document

public class User {

    /////////////////////////////////////////////////////////////////
    @Id
    private String id;

    /////////////////////////////////////////////////////////////////
    @NotBlank(message = "Name is required")
    private String name;
    /////////////////////////////////////////////////////////////////
    @NotBlank(message = "Username is required")
    @Indexed(unique = true)
    private String username;

    /////////////////////////////////////////////////////////////////
    @NotBlank(message = "Email is required")
    @Indexed(unique = true)
    private String email;

    /////////////////////////////////////////////////////////////////
    @NotBlank(message = "Password is required")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "Password must be at least 8 characters long and contain at least one letter and one number")
    private String password;

    /////////////////////////////////////////////////////////////////
    @NotEmpty(message = "At least one role is required")
    private Collection<Role> roles=new ArrayList<>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
}
