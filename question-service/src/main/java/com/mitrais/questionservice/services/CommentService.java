package com.mitrais.questionservice.services;

import com.mitrais.questionservice.models.Comment;

/**
 * Provide functionality to manipulate Comment input request.
 *
 * @author Rai Suardhyana Arijasa on 9/2/2019.
 */
public interface CommentService extends BaseService<Comment> {

    /**
     * get comment by id
     *
     * @param commentId id of comment
     * @return comment object
     */
    Comment getCommentById(Long commentId);

    /**
     * create comment
     *
     * @param body type Comment
     */
    void createComment(Comment body, Long postId);

    /**
     * update comment
     *
     * @param body Comment
     */
    void updateComment(Comment body);

    /**
     * Delete comment by id
     *
     * @param commentId id of comment
     */
    void deleteComment(Long commentId);
}
