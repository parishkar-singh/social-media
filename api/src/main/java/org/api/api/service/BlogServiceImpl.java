package org.api.api.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.api.api.model.Blog;
import org.api.api.repository.BlogRepository;
import org.api.api.utils.UserPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public String addNewBlog(String userId, Blog blog) {
        blog.setUserId(userId);
        Blog temp=blogRepository.save(blog);
        return temp.getBlogId();
    }

    @Override
    public Blog editBlog(String blogId, Blog updatedBlog) {
    		updatedBlog.setBlogId(blogId);
        	blogRepository.save(updatedBlog);

        return updatedBlog;
    }

    @Override
    public void deleteBlog(String userId, String blogId) {
    	
		Blog blog = blogRepository.findById(blogId).orElse(null);
        if (blog != null) {
            if(blog.getUserId().equals(userId)) {
                blogRepository.deleteById(blogId);
            }
        }
    }

    @Override
    public void addComment(String blogId, String commentId) {
        Blog existingBlog = blogRepository.findById(blogId).orElse(null);
        if (existingBlog != null) {
            existingBlog.getCommentIds().add(commentId);
            blogRepository.save(existingBlog);
        }
    }

    @Override
    public void addLike(String userId, String username, String blogId) {
        Blog existingBlog = blogRepository.findById(blogId).orElse(null);
        if (existingBlog != null) {
            existingBlog.getLikes().add(new UserPair(userId,username));
            blogRepository.save(existingBlog);
        }
    }

    @Override
    public List<Blog> getPersonalizedBlogs(ArrayList<String> categories) {
    	List<Blog>blogs=blogRepository.findByCategoriesIn(categories);
        blogs.removeIf(blog -> !"public".equals(blog.getVisibility()));

        Collections.sort(blogs, Comparator.comparing(Blog::getDate).reversed());

        return blogs;
    }

    @Override
    public List<Blog> getFeedBlogs(List<String> userIds) {
    	List<Blog> blogs=blogRepository.findByUserIdIn(userIds);

        Collections.sort(blogs, Comparator.comparing(Blog::getDate).reversed());
        
        return blogs;
    }



	@Override
	public List<UserPair> getLikes(String blogId) {
		Blog blog=blogRepository.findById(blogId).orElse(null);
		if(blog!=null) {
			return blog.getLikes();
		}
		
		return null;
		}

	@Override
	public Blog getBlog(String blogId, String userId) {
		Blog blog = blogRepository.findById(blogId).orElse(null);
        if (blog != null) {
            if(blog.getUserId().equals(userId)) {
            	return blog;
            }
        }
		return null;
	}

	@Override
	public List<Blog> getAllBlogs(String userId) {
		return blogRepository.findAllByUserId(userId);
	}

	@Override
	public List<String> getCommentIds(String blogId) {
		Blog blog=blogRepository.findById(blogId).orElse(null);
		if(blog!=null) {
			return blog.getCommentIds();
		}
		
		return null;
	}
}