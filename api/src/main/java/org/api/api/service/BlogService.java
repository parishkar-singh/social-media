package org.api.api.service;

import java.util.ArrayList;
import java.util.List;

import org.api.api.model.Blog;
import org.api.api.utils.Like;
import org.api.api.utils.LikesOnBlog;
import org.api.api.utils.UserPair;

public interface BlogService {

	
	//For UserController
	String addNewBlog(String userId, Blog blog);
	void editBlog(String blogId, Blog updatedBlog);
	void deleteBlog(String userId, String blogId);
	void addComment( String blogId, String commentId);// suggest usercontroller to call comment service as well
	void addLike(Like like);// suggest usercontroller to call comment service as well
	Blog getBlog(String userId, String blogId);
	List<Blog> getAllBlogsByUser(String userId);
	void deleteComment(String commentId, String blogId);
	
	//For BlogController
	List<Blog> getPersonalizedBlogs(ArrayList<String> categories);
	List<Blog> getFeedBlogs(List<String> userIds);
	LikesOnBlog getLikesOnBlog(String blogId);
	List<String> getCommentIds(String blogId);
	List<Blog> getAllPublicBlogs();
	List<Blog> getTrendingBlogs();
}
