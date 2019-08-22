package com.mitrais.questionservice.repositories;

import com.mitrais.questionservice.models.Post;
import com.mitrais.questionservice.models.Status;
import com.mitrais.questionservice.models.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Post repository
 */
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByTypeAndStatus(Type type, Status status);
}
