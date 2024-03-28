package org.api.api.utils;

public class UserPair {
private String username;
private String userId;


public UserPair(String username, String userId) {
	super();
	this.username = username;
	this.userId = userId;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}

}
