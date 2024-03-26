package org.api.api.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.api.api.model.Blog;
import org.api.api.model.Comment;
import org.api.api.model.User;
import org.api.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    org.api.api.utils.Logger UserServiceLogger = new org.api.api.utils.Logger("User Service");
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BlogService blogService;

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
    public User getUser(String username){
        Optional<User> p = userRepository.findById(username);
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
    public User updateUser(String userName, User updatedUser) {
        try {
            User existingUser = getUser(userName);
            if (existingUser != null) {
                if (updatedUser.getName() != null) {
                    existingUser.setName(updatedUser.getName());
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
    public void deleteUser(String username){
        try {
            if(getUser(username) != null)
            userRepository.deleteById(username);
        } catch (Exception e) {
            UserServiceLogger.logError("User not Deleted: "+e.getMessage());
            throw e;
        }
    }

    @Override
    public void addFollower(String username, String toBeFollowedUsername){
        try {
            User follower = getUser(username);
            User followee = getUser(toBeFollowedUsername);
    
            follower.getFollowing().add(toBeFollowedUsername);
            follower.getFollowers().add(username);
    
            updateUser(toBeFollowedUsername, followee);
            updateUser(username, follower);
        } catch (Exception e) {
            UserServiceLogger.logError("Error while adding follower: "+e.getMessage());
            throw e;
        }
    }

    @Override
    public void removeFollower(String username, String toBeRemovedUsername){
        try{
            User follower = getUser(username);
            User followee = getUser(toBeRemovedUsername);

            follower.getFollowing().remove(toBeRemovedUsername);
            followee.getFollowers().remove(username);

            updateUser(toBeRemovedUsername, followee);
            updateUser(username, follower);

        } catch (Exception e) {
            UserServiceLogger.logError("Error while adding follower: "+e.getMessage());
            throw e;
        }
    }

    @Override
    public void addNewBlog(String username, Blog blog){
        blogService.addNewBlog(username, blog);
    }
    @Override
    public void editBlog(String blog_id, Blog updatedBlog){
        blogService.editBlog(blog_id, updatedBlog);
    }
    @Override
    public void deleteBlog(String blog_id){
        blogService.deleteBlog(blog_id);
    }

    @Override
    public void addComment(String username, String blog_id, Comment comment){
        blogService.addComment(username, blog_id, comment);
    }

    @Override
    public void likeBlog(String username, String blog_id){
        blogService.likeBlog(username, blog_id);
    }

}