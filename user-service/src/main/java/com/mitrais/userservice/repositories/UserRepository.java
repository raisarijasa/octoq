package com.mitrais.userservice.repositories;

import com.mitrais.userservice.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    User findByEmail(String email);

    void delete(User user);
}
