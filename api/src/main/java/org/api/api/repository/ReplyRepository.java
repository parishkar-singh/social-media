package org.api.api.repository;

import org.api.api.model.Reply;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReplyRepository extends MongoRepository<Reply,String>{

}

