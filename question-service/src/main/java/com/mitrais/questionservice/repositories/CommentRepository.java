package com.mitrais.questionservice.repositories;

import com.mitrais.questionservice.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Comment Repository
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
