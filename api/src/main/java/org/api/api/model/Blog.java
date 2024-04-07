package org.api.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.api.api.utils.LikesOnBlog;
import org.api.api.utils.UserPair;
import org.springframework.data.annotation.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;

@Data
@NoArgsConstructor
public class Blog {

	@Id
	private String blogId;

	@NotBlank(message="Owner name is required")
	private String OwnerName;
	
	@NotBlank(message = "Blog writer id is required")
	private String uploaderId;

	@NotBlank(message = "Title is required")
	private String title;

	@NotBlank(message = "Description is required")
	private String description;

	@NotNull(message = "TimeStamp is required")
	private Date date = new Date();

	@NotNull(message = "Blog Categories is required")
	private ArrayList<String> categories;

	@NotNull(message = "Blog content is required")
	private String content;

	@NotNull(message = "Likes object is required")
	private LikesOnBlog likes=new LikesOnBlog();

	@NotNull(message = "Comments object is required")
	private ArrayList<String> commentIds = new ArrayList<>();

	@NotNull(message = "Visibility level is required")
	private String visibility;
	
	@NotNull(message="AverageReadingTime is required")
	private String avgReadingTime;
	
	private String blogPic;

	// Define your additional constructors if needed
}
