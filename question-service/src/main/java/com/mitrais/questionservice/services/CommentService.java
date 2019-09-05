package com.mitrais.questionservice.services;

import com.mitrais.questionservice.models.Comment;

/**
 * Provide functionality to manipulate Comment input request.
 *
 * @author Rai Suardhyana Arijasa on 9/2/2019.
 */
public interface CommentService extends BaseService<Comment> {

    /**
     * Provide functionality to retrieve comment by id.
     *
     * @param commentId id of comment
     * @return comment object
     */
    Comment getCommentById(Long commentId);

    /**
     * Provide functionality to create a new comment.
     *
     * @param body type Comment
     */
    void createComment(Comment body, Long postId);

    /**
     * Provide functionality to update comment.
     *
     * @param body Comment
     */
    void updateComment(Comment body);

    /**
     * Provide functionality to delete comment by id.
     *
     * @param commentId id of comment
     */
    void deleteComment(Long commentId);
}
