package org.api.api.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.api.api.model.Role;
import org.api.api.model.User;

import java.util.List;

public interface IUserService {
    User createUser(User user);

    Role createRole(Role role);
    User updateUser(@NotEmpty String userName, @Valid @NotNull User updatedUser);
    void addRoleToUser(String user, String role);

    User getUser(String username);

    List<User> getUsers();
}
