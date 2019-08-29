package com.mitrais.userservice.repositories;

import com.mitrais.userservice.models.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DataMongoTest
@ExtendWith(SpringExtension.class)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void testCreateUser_ReturnSuccess() {
        User user = User.builder().email("test@test.com").build();
        userRepository.save(user);
        Assertions.assertThat(user.getId()).isNotNull();
    }

    @Test
    void testDuplicateUser_ReturnFailed() {
        User user = User.builder().email("test@test.com").build();
        userRepository.save(user);
        User user2 = User.builder().email("test@test.com").build();

        try {
            userRepository.save(user2);
            Assertions.fail("duplicate key not thrown");
        } catch (Exception e) {
            Assertions.assertThat(e).isInstanceOf(DuplicateKeyException.class);
        }
    }

    @Test
    void testUpdateUser_ReturnSuccess() {
        String userName = "User 1";
        User user = User.builder().email("test@test.com").build();
        userRepository.save(user);
        user.setFullname(userName);
        userRepository.save(user);
        User userDb = userRepository.findByEmail(user.getEmail());
        Assertions.assertThat(userDb.getFullname()).isEqualToIgnoringCase(userName);
    }

    @Test
    void testGetUserByEmail_ReturnSuccess() {
        String userEmail = "test@test.com";
        User user = User.builder().email(userEmail).build();
        userRepository.save(user);
        User userDb = userRepository.findByEmail(user.getEmail());
        Assertions.assertThat(userDb.getEmail()).isEqualToIgnoringCase(userEmail);
    }

    @Test
    void testGetUserByEmailNotFound_ReturnNull() {
        String userEmail = "test@test.com";
        User userDb = userRepository.findByEmail(userEmail);
        Assertions.assertThat(userDb).isNull();
    }

    @Test
    void testDeleteUser_ReturnSuccess() {
        String userEmail = "test@test.com";
        User user = User.builder().email(userEmail).build();
        userRepository.save(user);
        userRepository.deleteById(user.getId());
        User userDb = userRepository.findByEmail(user.getEmail());
        Assertions.assertThat(userDb).isNull();
    }

    @Test
    void testDeleteUserNotExist_ReturnFail() {
        User user = User.builder().email("test@test.com").build();
        try {
            userRepository.delete(user);
            Assertions.fail("Fail");
        } catch (Exception e) {
            Assertions.assertThat(e).isInstanceOf(IllegalArgumentException.class);
        }
    }
}