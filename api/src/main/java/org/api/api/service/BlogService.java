package org.api.api.service;

import java.util.ArrayList;
import java.util.List;

import org.api.api.model.Blog;
import org.api.api.model.Comment;
import org.api.api.utils.UserPair;

public interface BlogService {

	
	//For UserController
	String addNewBlog(String userId, Blog blog);
	void editBlog(String blogId, Blog updatedBlog);
	void deleteBlog(String userId, String blogId);
	void addComment( String blogId, String commentId);// suggest usercontroller to call comment service as well
	void addLike(String userId,String username, String blogId);// suggest usercontroller to call comment service as well
	Blog getBlog(String userId, String blogId);
	List<Blog> getAllBlogs(String userId);
	
	
	//For BlogController
	List<Blog> getPersonalizedBlogs(ArrayList<String> categories);
	List<Blog> getFeedBlogs(List<String> userIds);
	List<UserPair> getLikes(String blogId);
	List<String> getCommentIds(String blogId);
	
}
