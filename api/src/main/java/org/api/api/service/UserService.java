package org.api.api.service;

import java.util.List;

import org.api.api.model.User;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public interface UserService {
    User createUser(User user); // insert new User
    
    User getUserById(String username);
    List<User> getUsers();
    
    User updateUser(@NotEmpty String username, @Valid @NotNull User updatedUser); //edit profile
    
    void deleteUser(String username);
     
    //Followers
    void addFollower(String username, String toBeFollowedUsername);// Adds to the "following" list of username and "followers" list of toBeFollowed
    void removeFollower(String username, String toBeRemovedUsername);// removes from the "following" list of  username and "followers" list of toBeRemovedUsername

    List<String> getAllFollowers(String username);
    List<String> getAllFollowing(String username);
    
    // void addNewBlog(String username, Blog blog);
    // void editBlog(String blog_id, Blog updatedBlog);
    // void deleteBlog(String blog_id);

    // void addComment(String username, String blog_id, Comment comment);

    // void addReply(String username, String blog_id, String comment_id, Reply reply);

    // void likeBlog(String username, String blog_id);
    // void removeLikeFromBlog(String username, String blog_id);

    // void addUpvote(String username, String blog_id, String comment_id);
    // void removeUpvote(String username, String blog_id, String comment_id);


}
