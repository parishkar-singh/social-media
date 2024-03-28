package org.api.api.repository;


import org.api.api.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository  extends MongoRepository<Comment,String> {
	
    //Role findByName(String name);

}