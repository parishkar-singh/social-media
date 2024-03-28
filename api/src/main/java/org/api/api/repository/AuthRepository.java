package org.api.api.repository;

import org.api.api.model.Auth;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends MongoRepository <Auth, String>{
    Optional<Auth> findByUsername(String username);
    boolean existsByUsername(String username);

}
