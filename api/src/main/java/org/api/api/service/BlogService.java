package org.api.api.service;

import org.api.api.model.Blog;
import org.api.api.model.Comment;

public class BlogService {

    public void addNewBlog(String username, Blog blog) {
        System.out.println("ADDING BLOG");
        return;
    }

    public void editBlog(String blog_id, Blog updatedBlog) {
        System.out.println("EDITING BLOG");
        return;
    }

    public void deleteBlog(String blog_id) {
        System.out.println("DELETE BLOG");
        return;
    }

    public void addComment(String username, String blog_id, Comment comment){
        System.out.println("ADDING COMMENT");
        return;
    }

    public void likeBlog(String username, String blog_id){
        System.out.println("ADDING LIKE");
        return;
    }

}
