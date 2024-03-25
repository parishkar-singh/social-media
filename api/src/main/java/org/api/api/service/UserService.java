package org.api.api.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.api.api.model.Role;
import org.api.api.model.User;
import org.api.api.repository.RoleRepository;
import org.api.api.repository.UserRepository;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements IUserService {
    org.api.api.utils.Logger UserServiceLogger = new org.api.api.utils.Logger("User Service");
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public User createUser(@Valid @NotNull User user) {
        try {
            UserServiceLogger.logDatabase("User Saved");
            return userRepository.save(user);
        } catch (Exception e) {
            UserServiceLogger.logError("Error saving user: " + e.getMessage());
            throw e;
        }
    }


    @Override
    public Role createRole(@Valid @NotNull Role role) {
        try {
            UserServiceLogger.logDatabase("Role Saved");
            return roleRepository.save(role);
        } catch (Exception e) {
            UserServiceLogger.logError("Error saving role: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public User updateUser(String userName, User updatedUser) {
        try {
            User existingUser = userRepository.findByUsername(userName);
            if (existingUser != null) {
                if (updatedUser.getName() != null) {
                    existingUser.setName(updatedUser.getName());
                }
                if (updatedUser.getEmail() != null) {
                    existingUser.setEmail(updatedUser.getEmail());
                }
                return userRepository.save(existingUser);
            } else {
                throw new RuntimeException("User not found");
            }
        } catch (Exception e) {
            UserServiceLogger.logError("User or Role not found"+e.getMessage());
            throw e;
        }
    }

    @Override
    public void addRoleToUser(@NotEmpty String userName, @NotEmpty String roleName) {
        try {
            User user = userRepository.findByUsername(userName);
            Role role = roleRepository.findByName(roleName);
            if (user != null && role != null) {
                user.getRoles().add(role);
                userRepository.save(user);
                UserServiceLogger.logDatabase("Role " + roleName + " added to User " + userName);
            } else {
                UserServiceLogger.logError("User or Role not found");
            }
        } catch (Exception e) {
            UserServiceLogger.logError("An error occurred while adding role to user: " + e.getMessage());
        }
    }

    @Override
    public User getUser(@NotEmpty String userName) {
        try {
            return userRepository.findByUsername(userName);
        } catch (Exception e) {
            UserServiceLogger.logError("Error getting user: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public List<User> getUsers() {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            UserServiceLogger.logError("Error getting users: " + e.getMessage());
            throw e;
        }
    }
}