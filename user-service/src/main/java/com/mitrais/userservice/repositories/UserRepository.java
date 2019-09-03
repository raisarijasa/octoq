package com.mitrais.userservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mitrais.userservice.models.User;

/**
 * Provide functionality to manipulate User data in database
 *
 * @author Rai Suardhyana Arijasa on 9/3/2019.
 */
public interface UserRepository extends MongoRepository<User, String> {

    User findByEmail(String email);

    void delete(User user);
}
