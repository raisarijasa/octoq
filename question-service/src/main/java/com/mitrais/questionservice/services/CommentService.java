package com.mitrais.questionservice.services;

import com.mitrais.questionservice.dto.CommentDto;
import com.mitrais.questionservice.models.Comment;

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
    CommentDto getCommentById(Long commentId);

    /**
     * create comment
     *
     * @param body type CommentDto
     */
    void createComment(CommentDto body, Long postId);

    /**
     * update comment
     *
     * @param body CommentDto
     */
    void updateComment(CommentDto body);

    /**
     * Delete comment by id
     *
     * @param commentId id of comment
     */
    void deleteComment(Long commentId);
}
