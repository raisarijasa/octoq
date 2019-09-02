package com.mitrais.questionservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mitrais.questionservice.models.Comment;

/**
 * Provide functionality to manipulate Comment data in database.
 *
 * @author Rai Suardhyana Arijasa on 9/2/2019.
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
