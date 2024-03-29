package org.api.api.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.api.api.model.Blog;
import org.api.api.repository.BlogRepository;
import org.api.api.utils.Like;
import org.api.api.utils.LikesOnBlog;
import org.api.api.utils.UserPair;
import org.springframework.stereotype.Service;

@Service
public class BlogServiceImpl implements BlogService {
    private final BlogRepository blogRepository;
    public BlogServiceImpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }
    
    @Override
    public String addNewBlog(String userId, Blog blog) {
        System.out.println("blogService called");
        blog.setUploaderId(userId);
        ArrayList<String> categories=blog.getCategories();
        ArrayList<String> newCategories=new ArrayList<>();
        for(String category:categories) {
        	newCategories.add(category.toLowerCase().trim());
        }
        blog.setCategories(newCategories);
        Blog savedBlog = blogRepository.save(blog);
        return savedBlog.getBlogId();
    }

    @Override
    public void editBlog(String userId, Blog updatedBlog) {
        if (!updatedBlog.getUploaderId().equals(userId)) {
            throw new RuntimeException("You are not authorized to edit this blog");
        }
    }

    @Override
    public void deleteBlog(String userId, String blogId) {
        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(() -> new RuntimeException("Blog not found with id: " + blogId));

        if (!blog.getUploaderId().equals(userId)) {
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
        System.out.println("what");
        existingBlog.getLikes().addLike(new UserPair(like.getUsername(), like.getUserId()));
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
        List<Blog> blogs = blogRepository.findByUploaderIdIn(userIds);
        blogs.sort(Comparator.comparing(Blog::getDate).reversed());
        return blogs;
    }

    @Override
    public LikesOnBlog getLikesOnBlog(String blogId) {
        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(() -> new RuntimeException("Blog not found with id: " + blogId));
        return blog.getLikes();
    }

    @Override
    public Blog getBlog(String userId, String blogId) {
        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(() -> new RuntimeException("Blog not found with id: " + blogId));

        if (!blog.getUploaderId().equals(userId)) {
            throw new RuntimeException("You are not authorized to access this blog");
        }
        return blog;
    }

    @Override
    public List<Blog> getAllBlogsByUser(String userId) {
        return blogRepository.findAllByUploaderId(userId);
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
    // Function to calculate percentile for Dates
    private static double calculatePercentileForDate(List<Date> list) {
        int n = list.size();
        double percentile = 0.5; // 50th percentile

        int index = (int) Math.ceil(percentile * n);
        return list.get(index - 1).getTime();
    }

    // Function to calculate percentile for integers
    private static double calculatePercentileForLikes(List<Integer> list) {
        int n = list.size();
        double percentile = 0.5; // 50th percentile

        int index = (int) Math.ceil(percentile * n);
        return list.get(index - 1);
    }
    @Override
    public List<Blog> getTrendingBlogs() {
        BlogService blogService=new BlogServiceImpl(blogRepository);
        List<Blog> publicBlogs=blogService.getAllPublicBlogs();
        ArrayList<Integer> numberOfLikesList=new ArrayList<>();
        ArrayList<Date> dateOfBlogPostList=new ArrayList<>();
        for(Blog blog:publicBlogs){
            numberOfLikesList.add(blogService.getLikesOnBlog(blog.getBlogId()).getLikes());
            dateOfBlogPostList.add(blog.getDate());
        }
        Collections.sort(dateOfBlogPostList, Date::compareTo);
        Collections.sort(numberOfLikesList);

        // Calculate percentiles
        double datePostedPercentile = calculatePercentileForDate(dateOfBlogPostList);
        double numberOfLikesPercentile = calculatePercentileForLikes(numberOfLikesList);
        Comparator<Blog> comparator = new Comparator<Blog>() {
            @Override
            public int compare(Blog blog1, Blog blog2) {
                // Calculate distance from the percentiles for each blog
                double distance1 = Math.abs(blog1.getDate().getTime() - datePostedPercentile) +
                        Math.abs(blog1.getLikes().getLikes() - numberOfLikesPercentile);
                double distance2 = Math.abs(blog2.getDate().getTime() - datePostedPercentile) +
                        Math.abs(blog2.getLikes().getLikes() - numberOfLikesPercentile);

                // Sort based on distance (lower distance is better)
                return Double.compare(distance1, distance2);
            }
        };
        Collections.sort(publicBlogs, comparator);
        return publicBlogs;
    }
}