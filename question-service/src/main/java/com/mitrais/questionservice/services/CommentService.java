package com.mitrais.questionservice.services;

import com.mitrais.questionservice.dto.CommentDto;
import com.mitrais.questionservice.models.Comment;
import org.springframework.http.ResponseEntity;

/**
 * Comment service
 */
public interface CommentService extends BaseService<Comment> {

    /**
     * get comment by id
     *
     * @param commentId id of comment
     * @return response entity object
     */
    ResponseEntity getCommentById(Long commentId);

    /**
     * create comment
     *
     * @param body type CommentDto
     * @return response entity object
     */
    ResponseEntity createComment(CommentDto body);

    /**
     * update comment
     *
     * @param body CommentDto
     * @return response entity
     */
    ResponseEntity updateComment(CommentDto body);

    /**
     * Delete comment by id
     *
     * @param commentId id of comment
     * @return response entity object
     */
    ResponseEntity deleteComment(Long commentId);
}
