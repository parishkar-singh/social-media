package org.api.api.model;

import java.util.Date;

import java.util.ArrayList;

import org.api.api.utils.UserPair;
import org.springframework.data.annotation.Id;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Blog {
	
@Id
private String blogId;

@NotBlank(message="Blog writer id is required")
private String userId;

@NotBlank(message="Title is required")
private String title;

@NotBlank(message="Description is required")
private String description;

@NotNull(message="TimeStamp is required")
private Date date=new Date();

@NotNull(message="Blog Categories is required")
private ArrayList<String> categories;

@NotNull(message="Blog content is required")
private String content;

@NotNull(message="Likes object is required")
private ArrayList<UserPair> likes=new ArrayList<UserPair>();

@NotNull(message = "Comments object is required")
private ArrayList<String> commentIds=new ArrayList<String>();

@NotNull(message="Visibility level is required")
private String visibility;

private String blogPic;
public Blog() {}

public Blog(String blogId, @NotBlank(message = "Blog writer id is required") String userId,
        @NotBlank(message = "Title is required") String title,
        @NotBlank(message = "Description is required") String description,
        @NotNull(message = "Blog Categories is required") ArrayList<String> categories,
        @NotNull(message = "Blog content is required") String content,
        @NotNull(message = "Likes object is required") ArrayList<UserPair> likes,
        @NotNull(message = "Comments object is required") ArrayList<String> commentIds,
        @NotNull(message = "Visibility level is required") String visibility) {
super();
this.blogId = blogId;
this.userId = userId;
this.title = title;
this.description = description;
this.date = new Date();
this.categories = categories;
this.content = content;
if (likes != null) { // Check if likes is null before assigning
    this.likes = likes;
}
if (commentIds != null) { // Check if commentIds is null before assigning
    this.commentIds = commentIds;
}
this.visibility = visibility;
}
public Blog(String blogId, String userId, String title, String description,
		ArrayList<String> categories, String content, ArrayList<UserPair> likes, ArrayList<String> commentIds,
		String visibility, String blogPic) {
	super();
	this.blogId = blogId;
	this.userId = userId;
	this.title = title;
	this.description = description;
	this.date = new Date();
	this.categories = categories;
	this.content = content;
	this.likes = likes;
	this.commentIds = commentIds;
	this.visibility = visibility;
	this.blogPic=blogPic;
}
public String getBlogId() {
	return blogId;
}
public void setBlogId(String blogId) {
	this.blogId = blogId;
}
public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}

public ArrayList<String> getCategories() {
	return categories;
}
public void setCategories(ArrayList<String> categories) {
	this.categories = categories;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public ArrayList<UserPair> getLikes() {
	return likes;
}
public void addLike(UserPair userPair) {

	this.likes.add(userPair);
}

public String getVisibility() {
	return visibility;
}
public void setVisibility(String visibility) {
	this.visibility = visibility;
}
public ArrayList<String> getCommentIds() {
	return commentIds;
}
public void setCommentIds(ArrayList<String> commentIds) {
	this.commentIds = commentIds;
}
public void setLikes(ArrayList<UserPair> likes) {
	this.likes = likes;
}


public Date getDate() { // Getter for date field
    return date;
}

public void setDate(Date date) { // Setter for date field
    this.date = new Date();
}
//public String getdate() {
//	return date;
//}
//public void setDate(String date) {
//	this.date = date;
//}
}
