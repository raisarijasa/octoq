package com.mitrais.userservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mitrais.userservice.models.Role;

/**
 * Provide functionality to manipulate Role data in database
 *
 * @author Rai Suardhyana Arijasa on 9/3/2019.
 */
public interface RoleRepository extends MongoRepository<Role, String> {

    Role findByRole(String role);
}
