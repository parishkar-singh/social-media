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

@Data // getters, setters, toString, equals, and hashCode methods.
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Reply {
    
    @Id
    private String replyId;

    @Indexed(unique = true)
    @NotBlank(message = "userID is required")
    private String userId;
    
    @Indexed(unique = true)
    @NotBlank(message = "commentID is required")
    private String commentId;
   
    @NotBlank(message = "Text is required")
    private String text;

    int upvotes;

    private Collection<String> replyIDs=new ArrayList<>();
    
    public String getReplyId() {
    	return replyId;
    }
    public String getCommentId() {
		return commentId;
	}
}
