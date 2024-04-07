package org.api.api.service;

import java.util.List;
import java.util.Optional;

import org.api.api.model.User;
import org.api.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    org.api.api.utils.Logger UserServiceLogger = new org.api.api.utils.Logger("User Service");
    
    @Autowired
    private UserRepository userRepository;

    @SuppressWarnings("null")
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
    public User getUserById(@Valid @NotNull String userId){
        System.out.println();
        Optional<User> p = userRepository.findById(userId);
        if (p.isPresent()) {
            return p.get();
        }
        return null;
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
    
    @Override
    public User updateUser(@Valid @NotNull String userId, @Valid @NotNull User updatedUser) {
        try {
            User existingUser = getUserById(userId);
            if (existingUser != null) {
                if (updatedUser.getName() != null) {
                    existingUser.setName(updatedUser.getName());
                }
                if (updatedUser.getProfilePic() != null) {
                    existingUser.setProfilePic(updatedUser.getProfilePic());
                }
                if (updatedUser.getEmail() != null) {
                    existingUser.setEmail(updatedUser.getEmail());
                }
                if (updatedUser.getPassword() != null) {
                    existingUser.setPassword(updatedUser.getPassword());
                }
                if (updatedUser.getUsername() != null) {
                    existingUser.setUsername(updatedUser.getUsername());
                }
                if(updatedUser.getFollowing() != null){
                    existingUser.setFollowing(updatedUser.getFollowing());
                }
                if(updatedUser.getFollowers() != null){
                    existingUser.setFollowers(updatedUser.getFollowers());
                }
                if(updatedUser.getBlogIds() != null){
                    existingUser.setBlogIds(updatedUser.getBlogIds());
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
    public void deleteUser(@Valid @NotNull String userId){
        try {
            if(getUserById(userId) != null){
            userRepository.deleteById(userId);
            }
        } catch (Exception e) {
            UserServiceLogger.logError("User not Deleted: "+e.getMessage());
            throw e;
        }
    }

    @Override
    public void addFollower(@Valid @NotNull String userId, @Valid @NotNull String toBeFolloweduserId){
        try {
            User follower = getUserById(userId);
            User followee = getUserById(toBeFolloweduserId);
    
            follower.getFollowing().add(toBeFolloweduserId);
            followee.getFollowers().add(userId);
    
            updateUser(toBeFolloweduserId, followee);
            updateUser(userId, follower);
        } catch (Exception e) {
            UserServiceLogger.logError("Error while adding follower: "+e.getMessage());
            throw e;
        }
    }

    @Override
    public void removeFollower(@Valid @NotNull String userId, @Valid @NotNull String toBeRemoveduserId){
        try{
            User follower = getUserById(userId);
            User followee = getUserById(toBeRemoveduserId);

            follower.getFollowing().remove(toBeRemoveduserId);
            followee.getFollowers().remove(userId);

            updateUser(toBeRemoveduserId, followee);
            updateUser(userId, follower);

        } catch (Exception e) {
            UserServiceLogger.logError("Error while adding follower: "+e.getMessage());
            throw e;
        }
    }

    @Override
    public List<String> getAllFollowers(@Valid @NotNull String userId){
        try {
            return getUserById(userId).getFollowers(); 
        } catch (Exception e) {
            UserServiceLogger.logError("Error while getting follower: "+e.getMessage());
            throw e;
        }
        
    }

    @Override
    public List<String> getAllFollowing(@Valid @NotNull String userId){
        try {
            return getUserById(userId).getFollowing();
        } catch (Exception e) {
            UserServiceLogger.logError("Error while getting following: "+e.getMessage());
            throw e;
        }
    }
}