package org.api.api.controller;

import java.util.List;

import org.api.api.model.Blog;
import org.api.api.model.Comment;
import org.api.api.model.Reply;
import org.api.api.model.User;
import org.api.api.service.BlogService;
import org.api.api.service.CommentService;
import org.api.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
// @CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    @Autowired
    private  UserService userService;
    org.api.api.utils.Logger userControllerLogger = new org.api.api.utils.Logger("User Controller");

    @Autowired
    private BlogService blogService; 
    
    @PostMapping("/api/user")
    public void createUser(@RequestBody @Valid @NotNull User user) {
        try {
            User createdUser = userService.createUser(user);
            userControllerLogger.logSuccess("User Created: "+ createdUser);
            //return createdUser;
        } catch (Exception e) {
            userControllerLogger.logError("Error creating User");
            throw e;
        }
    }

    @GetMapping("/api/users")
    public List<User> getUsers() {
        try {
            List<User> users = userService.getUsers();
            userControllerLogger.logSuccess("User Fetched");
            return users;
        } catch (Exception e) {
            userControllerLogger.logError("Error Getting the User");
            throw e;
        }
    }

    @GetMapping("/api/user/{userId}")
    public User getUser(@PathVariable("userId") String userId) {
        try {
            User user = userService.getUserById(userId);
            System.out.println(user);
            userControllerLogger.logSuccess("User Fetched");
            return user;
        } catch (Exception e) {
            userControllerLogger.logError("Error Getting User: " + e.getMessage());
            return null;
        }
    }


    @PutMapping("/api/user/{userId}")
    public void updateUser(@RequestBody @Valid @NotNull User user, @PathVariable("userId") String userId) {
        try {
            User updatedUser = userService.updateUser(userId, user);
            userControllerLogger.logSuccess("Updated User: " + updatedUser);
            // return updatedUser;
        } catch (Exception e) {
            userControllerLogger.logError("Error Updating User");
            throw e;
        }
    }

    @DeleteMapping("/api/user/{userId}")
    public void deleteUser(@PathVariable("userId") String userId){
        try {
            userService.deleteUser(userId);
            userControllerLogger.logSuccess("Deleted User " + userId);
        } catch (Exception e) {
            userControllerLogger.logError("Error Deleting User");
            throw e;
        }
    }

    @PostMapping("/api/user/blog/{userId}")
    public void addBlog(@RequestBody @Valid @NotNull Blog blog, @PathVariable("userId") String userId){
        try {
            String blogId = blogService.addNewBlog(userId, blog);
            userService.getUserById(userId).getBlogIds().add(blogId);
            userControllerLogger.logSuccess("Added Blog");
        } catch (Exception e) {
            userControllerLogger.logError("Error adding blog");
            throw e;
        }
    }

    @GetMapping("/api/user/blog/{userId}/{blogId}")
    public Blog getBlog(@PathVariable("userId") String userId, @PathVariable("blogId") String blogId){
        try {
            Blog temp = blogService.getBlog(userId, blogId);
            userControllerLogger.logSuccess("Got User's Blog ");
            return temp;
        } catch (Exception e) {
            userControllerLogger.logError("Error getting your blog");
            throw e;
        }
    }

    @GetMapping("/api/user/blog/{userId}")
    public List<Blog> getAllBlogs(@PathVariable("userId") String userId){
        try {
            List<Blog> temp = blogService.getAllBlogs(userId);
            userControllerLogger.logSuccess("Got User's Blog ");
            return temp;
        } catch (Exception e) {
            userControllerLogger.logError("Error getting user's blog");
            throw e;
        }
    }

    @PutMapping("/api/user/blog/{userId}")
    public void editBlog(@RequestBody @Valid @NotNull Blog blog, @PathVariable("userId") String userId){
        try {
            Blog temp = blogService.editBlog(userId, blog);
            userControllerLogger.logSuccess("Edited User's Blog ");
            // return temp;
        } catch (Exception e) {
            userControllerLogger.logError("Error editting user's blog");
            throw e;
        }
    }

    @DeleteMapping("/api/user/blog/{userId}/{blogId}")
    public void deleteBlog(@PathVariable("userId") String userId, @PathVariable("blogId") String blogId){
        try {
            blogService.deleteBlog(userId, blogId); // is this needed???
            userService.getUserById(userId).getBlogIds().remove(blogId);
            userControllerLogger.logSuccess("Deleted User's Blog ");
        } catch (Exception e) {
            userControllerLogger.logError("Error deleting your blog");
            throw e;
        }
    }


    @PostMapping("/api/user/{userId_1}/{userId_2}")
    public void addFollower(@PathVariable("userId_1") String follower, @PathVariable("userId_2") String followee){
        try {
            userService.addFollower(follower, followee);
            userControllerLogger.logSuccess("Added Follower");
        } catch (Exception e) {
            userControllerLogger.logError("Error adding follower");
            throw e;
        }

    }

    @PutMapping("/api/user/{userId_1}/{userId_2}")
    public void removeFollower(@PathVariable("userId_1") String follower, @PathVariable("userId_2") String followee){
        try {
            userService.removeFollower(follower, followee);
            userControllerLogger.logSuccess("Removed Follower");
        } catch (Exception e) {
            userControllerLogger.logError("Error removing follower");
            throw e;
        }
    }


}