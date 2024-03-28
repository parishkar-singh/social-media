package org.api.api.repository;

import java.util.Optional;

import org.api.api.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository  extends MongoRepository<Role,String> {
	Role findByName(String name);
}
