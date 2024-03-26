package org.api.api.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import org.api.api.model.Blog;
import org.api.api.model.Comment;
import org.api.api.model.User;

import java.util.List;

public interface UserService {
    User createUser(User user); // insert new User
    
    User getUser(String username);
    List<User> getUsers();
    
    User updateUser(@NotEmpty String username, @Valid @NotNull User updatedUser); //edit profile
    
    void deleteUser(String username);
     
    //Followers
    void addFollower(String username, String toBeFollowedUsername);// Adds to the "following" list of username and "followers" list of toBeFollowed
    void removeFollower(String username, String toBeRemovedUsername);// removes from the "following" list of  username and "followers" list of toBeRemovedUsername

    void addNewBlog(String username, Blog blog);
    void editBlog(String blog_id, Blog updatedBlog);
    void deleteBlog(String blog_id);

    void addComment(String username, String blog_id, Comment comment);
    void likeBlog(String username, String blog_id);
}
