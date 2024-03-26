package org.api.api.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.api.api.forms.UserRoleForm;
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
// @CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    @Autowired
    private  UserService userService;
    org.api.api.utils.Logger userControllerLogger = new org.api.api.utils.Logger("User Controller");

    @GetMapping("/api/users")
    public List<User> getUsers() {
        try {
            List<User> users = userService.getUsers();
            userControllerLogger.logSuccess("Users Fetched");
            return users;
        } catch (Exception e) {
            userControllerLogger.logError("Error Getting Users");
            return null;
        }
    }

    @PostMapping("/user")
    public User createUser(@RequestBody @Valid @NotNull User user) {
        try {
            User createdUser = userService.createUser(user);
            userControllerLogger.logSuccess("User Created: "+ createdUser);
            return createdUser;
        } catch (Exception e) {
            userControllerLogger.logError("Error creating User");
            return null;
        }
    }

    @PutMapping("/user")
    public User updateUser(@RequestBody @Valid @NotNull User user) {
        try {
            String username = user.getUsername();
            User updatedUser = userService.updateUser(username, user);
            userControllerLogger.logSuccess("Updated User: " + updatedUser);
            return updatedUser;
        } catch (Exception e) {
            userControllerLogger.logError("Error Updating User");
            return null;
        }
    }
}
