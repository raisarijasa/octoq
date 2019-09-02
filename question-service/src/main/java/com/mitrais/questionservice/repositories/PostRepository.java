package com.mitrais.questionservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mitrais.questionservice.dto.PostDto;
import com.mitrais.questionservice.models.Post;
import com.mitrais.questionservice.models.Status;
import com.mitrais.questionservice.models.Type;

/**
 * Provide functionality to manipulate Question and Answer data in database.
 *
 * @author Rai Suardhyana Arijasa on 9/2/2019.
 */
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByTypeAndStatus(Type type, Status status);
    List<PostDto> findByType(Type type);
}
