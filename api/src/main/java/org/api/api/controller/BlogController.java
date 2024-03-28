package org.api.api.controller;
import org.api.api.model.Blog;
import org.api.api.model.Comment;
import org.api.api.service.BlogService;
import org.api.api.service.CommentService;
import org.api.api.service.UserService;
import org.api.api.utils.UserPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/blogs")
public class BlogController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    
    @PostMapping("/{userId}")
    public ResponseEntity<String> addNewBlog(@PathVariable("userId") String userId, @RequestBody @Valid @NotNull Blog blog) {
        String blogId=blogService.addNewBlog(userId, blog);
        System.out.println(blogId);
        return new ResponseEntity<>("Blog created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{blogId}")
    public ResponseEntity<String> editBlog(@PathVariable String blogId, @RequestBody Blog updatedBlog) {
        blogService.editBlog(blogId, updatedBlog);	
        return new ResponseEntity<>("Blog created successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/{userId}/{blogId}")
    public ResponseEntity<String> deleteBlog(@PathVariable String userId, @PathVariable String blogId) {
        blogService.deleteBlog(userId,blogId);
        return new ResponseEntity<>("Blog deleted successfully", HttpStatus.OK);
    }

    @PostMapping("/{blogId}/comment")
    public ResponseEntity<String> addComment( @PathVariable String blogId, @RequestBody String commentId) {

        blogService.addComment(blogId, commentId);

        return new ResponseEntity<>("Comment added successfully", HttpStatus.CREATED);
    }

    @PostMapping("/{userId}/{blogId}/likes")
    public ResponseEntity<String> addLike(@PathVariable String userId,@PathVariable String username, @PathVariable String blogId) {
        blogService.addLike(userId,username, blogId);
        return new ResponseEntity<>("Like added successfully", HttpStatus.OK);
    }
    
    @GetMapping("/{blogId}/{userId}/getBlog")
    public Blog getBlog(@PathVariable String blogId, @PathVariable String userId) {
        return blogService.getBlog(blogId, userId);
    }
    @GetMapping("/{userId}/getBlogs")
    public List<Blog> getBlogsByUser(@PathVariable String userId) {
        return blogService.getAllBlogs( userId);
    }
    @GetMapping("/{blogId}/comments")
    public List<Comment> getComments(@PathVariable String blogId) {
    	List<String>commentIds=blogService.getCommentIds(blogId);
        return commentService.getComments((ArrayList<String>) commentIds);
    }    
    @GetMapping("/{blogId}/likes")
    public List<UserPair> getLikes(@PathVariable String blogId) {
    	return blogService.getLikes(blogId);
    }    
    
    @GetMapping("/personalized")
    public ResponseEntity<List<Blog>> getPersonalizedBlogs(@RequestBody List<String> categories) {
        List<Blog> personalizedBlogs = blogService.getPersonalizedBlogs(new ArrayList<>(categories));
        return new ResponseEntity<>(personalizedBlogs, HttpStatus.OK);
    }

    @GetMapping("/feed/{userId}")
    public ResponseEntity<List<Blog>> getFeedBlogs(@PathVariable String userId) {
    	List<String> userFollowingIds=userService.getAllFollowing(userId);
        List<Blog> feedBlogs = blogService.getFeedBlogs(userFollowingIds);
        return new ResponseEntity<>(feedBlogs, HttpStatus.OK);
    }
    
}
