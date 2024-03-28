package org.api.api.repository;

import org.api.api.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String> {
        User findByUsername(String username);
        User findByEmail(String email);
}