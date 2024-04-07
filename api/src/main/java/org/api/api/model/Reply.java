package org.api.api.model;


import lombok.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Data // getters, setters, toString, equals, and hashCode methods.
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Reply {
    
    @Id
    private String replyId;

    @NotBlank(message = "userID is required")
    private String userId;
    
    @NotBlank(message = "commentID is required")
    private String commentId;
   
    @NotBlank(message = "Text is required")
    private String text;

    
Date timestamp =new Date();
    
    
    

	public Date getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public void setReplyId(String replyId) {
		this.replyId = replyId;
	}


	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

    
    public String getReplyId() {
    	return replyId;
    }
    public String getCommentId() {
		return commentId;
	}
}
