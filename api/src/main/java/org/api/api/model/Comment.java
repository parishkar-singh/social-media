package org.api.api.model;
import java.util.Date;
import lombok.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collection;

@Data // getters, setters, toString, equals, and hashCode methods.
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Comment {
    
    @Id
    private String commentId;

    @NotBlank(message = "userId is required")
    private String userId;
    
    @NotBlank(message = "username is required")
    private String username;
    
    @NotBlank(message = "blogId required")
    private String blogId;
    
    
	@NotBlank(message = "Text is required")
    private String text;

     int upvotes;
     

    private ArrayList<String> replyIDs=new ArrayList<>();
    
    Date timestamp =new Date();
    
    
    

	public Date getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}


	public String getcommentId() {
		// TODO Auto-generated method stub
		return commentId;
	}
	

	public void setCommentId(String commentId) {
		this.commentId = commentId;
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public ArrayList<String> getReplyIDs() {
		return replyIDs;
	}

	public void setReplyIDs(ArrayList<String> replyIDs) {
		this.replyIDs = replyIDs;
	}
	
	public int getUpvotes() {
		return upvotes;
	}

	public void setUpvotes(int upvotes) {
		this.upvotes =upvotes;
	}
	
	public String getBlogId() {
		return blogId;
	}


	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}
	
	//methods to modify upvotes
	public void addReplyIds(String newReplyId) {
		replyIDs.add(newReplyId);
	}
	public void deleteReplyIds(String oldReplyId) {
		replyIDs.remove(oldReplyId);
	}
	
}