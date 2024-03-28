package org.api.api.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.api.api.forms.UserRoleForm;
import org.api.api.model.Role;
import org.api.api.model.User;
import org.api.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

	@Autowired
    private UserService userService;
    org.api.api.utils.Logger userControllerLogger = new org.api.api.utils.Logger("User Controller");

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        try {
            List<User> users = userService.getUsers();
            userControllerLogger.logSuccess("Users Fetched");
            return ResponseEntity.status(HttpStatus.CREATED).body(users);
        } catch (Exception e) {
            userControllerLogger.logError("Error Getting Users");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody @Valid @NotNull User user) {
        try {
            User createdUser = userService.createUser(user);
            URI uri=URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/").toUriString());
            userControllerLogger.logSuccess("User Created: "+ createdUser);
            return ResponseEntity.ok().body(createdUser);
        } catch (Exception e) {
            userControllerLogger.logError("Error creating User");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @PutMapping("/user")
    public ResponseEntity<User> updateUser(@RequestBody @Valid @NotNull User user) {
        try {
            String username = user.getUsername();
            User updatedUser = userService.updateUser(username, user);
            userControllerLogger.logSuccess("Updated User: " + updatedUser);
            return ResponseEntity.ok().body(updatedUser);
        } catch (Exception e) {
            userControllerLogger.logError("Error Updating User");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null); // Return empty body or handle the error as needed
        }
    }
    @PutMapping("/user/role")
    public ResponseEntity<Role> saveRole(@RequestBody @Valid @NotNull Role role) {
        try {
            URI uri=URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/role").toUriString());
            return ResponseEntity.ok().body(userService.createRole(role));
        } catch (Exception e) {
            userControllerLogger.logError("Error adding role to the  User");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
    @PostMapping("/user/role/extendToUser")
    public ResponseEntity<?> addRoleToUser(@RequestBody @Valid @NotNull UserRoleForm form) {
        try {
//            URI uri=URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/role").toUriString());
            userService.addRoleToUser(form.getUsername(), form.getRoleName());
            userControllerLogger.logSuccess("Role appended to User Successfully");
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            userControllerLogger.logError("Error adding role to the  User");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
    @GetMapping("/user/{email}")
    public User getUserByemail(@RequestBody String email){
    	return userService.getUsersByEmail(email);
    }
}
