package org.api.api.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.api.api.model.Blog;
import org.api.api.repository.BlogRepository;
import org.api.api.utils.Like;
import org.api.api.utils.UserPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogServiceImpl implements BlogService {
    private final BlogRepository blogRepository;
    public BlogServiceImpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }
    @Override
    public String addNewBlog(String userId, Blog blog) {
        blog.setUserId(userId);
        Blog savedBlog = blogRepository.save(blog);
        return savedBlog.getBlogId();
    }

    @Override
    public Blog editBlog(String userId, Blog updatedBlog) {
        if (!updatedBlog.getUserId().equals(userId)) {
            throw new RuntimeException("You are not authorized to edit this blog");
        }
        return blogRepository.save(updatedBlog);
    }

    @Override
    public void deleteBlog(String userId, String blogId) {
        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(() -> new RuntimeException("Blog not found with id: " + blogId));

        if (!blog.getUserId().equals(userId)) {
            throw new RuntimeException("You are not authorized to delete this blog");
        }

        blogRepository.deleteById(blogId);
    }

    @Override
    public void addComment(String blogId, String commentId) {
        Blog existingBlog = blogRepository.findById(blogId)
                .orElseThrow(() -> new RuntimeException("Blog not found with id: " + blogId));

        existingBlog.getCommentIds().add(commentId);
        blogRepository.save(existingBlog);
    }

    @Override
    public void addLike(Like like) {
        Blog existingBlog = blogRepository.findById(like.getBlogId())
                .orElseThrow(() -> new RuntimeException("Blog not found with id: " + like.getBlogId()));

        existingBlog.getLikes().add(new UserPair(like.getUserId(), like.getUsername()));
        blogRepository.save(existingBlog);
    }

    @Override
    public List<Blog> getPersonalizedBlogs(ArrayList<String> categories) {
        List<Blog> blogs = blogRepository.findByCategoriesIn(categories);
        blogs.removeIf(blog -> !"public".equals(blog.getVisibility()));
        blogs.sort(Comparator.comparing(Blog::getDate).reversed());
        return blogs;
    }

    @Override
    public List<Blog> getFeedBlogs(List<String> userIds) {
        List<Blog> blogs = blogRepository.findByUserIdIn(userIds);
        blogs.sort(Comparator.comparing(Blog::getDate).reversed());
        return blogs;
    }

    @Override
    public List<UserPair> getLikes(String blogId) {
        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(() -> new RuntimeException("Blog not found with id: " + blogId));
        return blog.getLikes();
    }

    @Override
    public Blog getBlog(String userId, String blogId) {
        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(() -> new RuntimeException("Blog not found with id: " + blogId));

        if (!blog.getUserId().equals(userId)) {
            throw new RuntimeException("You are not authorized to access this blog");
        }
        return blog;
    }

    @Override
    public List<Blog> getAllBlogsByUser(String userId) {
        return blogRepository.findAllByUserId(userId);
    }

    @Override
    public List<String> getCommentIds(String blogId) {
        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(() -> new RuntimeException("Blog not found with id: " + blogId));
        return blog.getCommentIds();
    }

    @Override
    public void deleteComment(String commentId, String blogId) {
        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(() -> new RuntimeException("Blog not found with id: " + blogId));

        if (!blog.getCommentIds().remove(commentId)) {
            throw new RuntimeException("Comment not found with id: " + commentId);
        }
        blogRepository.save(blog);
    }

    @Override
    public List<Blog> getAllPublicBlogs() {
        return blogRepository.findAll();
    }
}