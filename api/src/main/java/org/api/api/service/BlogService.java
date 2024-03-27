package org.api.api.service;

import java.util.List;

import org.api.api.model.Blog;
import org.api.api.model.Comment;
import org.api.api.model.Reply;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Service
public class BlogService {

    public String addNewBlog(String userId, Blog blog) {
        System.out.println("ADDING BLOG");
        return null;
    }

    public Blog editBlog(String blogId, Blog updatedBlog) {
        System.out.println("EDITING BLOG");
        return null;
    }

    public void deleteBlog(String userId, String blogId) {
        System.out.println("DELETE BLOG");
        return;
    }

    public Comment addComment(String userId, String blogId, Comment comment){
        System.out.println("ADDING COMMENT");
        return null;
    }

    public void likeBlog(String userId, String blogId){
        System.out.println("ADDING LIKE");
        return;
    }

    public Blog getBlog(String userId, String blogId){
        return null;
    }
    
    public List<Blog> getAllBlogs(String userId){
        return null;
    }

    public Reply addReply(String userId, String blogId, String commentId, @Valid @NotNull Reply reply) {
        return null;
    }

}
