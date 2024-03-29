package org.api.api.repository;

import java.util.ArrayList;
import java.util.List;

import org.api.api.model.Blog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface BlogRepository extends MongoRepository<Blog,String>  {

	List<Blog> findByCategoriesIn(ArrayList<String> categories);

	List<Blog> findByUploaderIdIn(List<String> followedUsers);

	List<Blog> findAllByUploaderId(String username);
	@Query("{'visibility': 'public'}")
	List<Blog> findAllPublicBlogs();


}
