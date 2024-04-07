package org.api.api.utils;

public class Like {
	private String userId;
private String username;
private String blogId;
public Like(String userId, String username, String blogId) {
	super();
	this.userId = userId;
	this.username = username;
	this.blogId = blogId;
}
public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getBlogId() {
	return blogId;
}
public void setBlogId(String blogId) {
	this.blogId = blogId;
}

}
