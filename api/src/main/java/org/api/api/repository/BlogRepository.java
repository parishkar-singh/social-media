package org.api.api.repository;

import java.util.ArrayList;
import java.util.List;

import org.api.api.model.Blog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BlogRepository extends MongoRepository<Blog,String>  {

	List<Blog> findByCategoriesIn(ArrayList<String> categories);

	List<Blog> findByUserIdIn(List<String> followedUsers);
	
	List<Blog> findAllByUserId(String username);



}