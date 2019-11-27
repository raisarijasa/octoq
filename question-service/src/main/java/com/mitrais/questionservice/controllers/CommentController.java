package com.mitrais.questionservice.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.mitrais.questionservice.controllers.requests.CommentRequest;
import com.mitrais.questionservice.controllers.requests.GroupRequest;
import com.mitrais.questionservice.models.Comment;
import com.mitrais.questionservice.repositories.MessageRepository;
import com.mitrais.questionservice.services.CommentService;

import static org.springframework.http.ResponseEntity.ok;

/**
 * Provide functionality to manipulate Comment data request.
 *
 * @author Rai Suardhyana Arijasa on 9/2/2019.
 */
@RestController
@RequestMapping("/comments")
public class CommentController extends BaseController<Comment> {
    private final CommentService commentService;
    private final MessageRepository messageRepository;

    @Autowired
    public CommentController(CommentService commentService, MessageRepository messageRepository) {
        this.commentService = commentService;
        this.messageRepository = messageRepository;
    }

    /**
     * Provide functionality to retrieve comment by id.
     *
     * @param commentId id of comment
     * @return response entity object
     */
    @GetMapping("/{commentId}")
    public ResponseEntity getCommentById(@PathVariable Long commentId) {
        if (commentId == null || commentId == 0) {
            return idMandatoryResponse();
        }
        List<Comment> comments = new ArrayList<>();
        comments.add(commentService.getCommentById(commentId));
        return getCommentByIdResponse(comments);
    }

    /**
     * Provide functionality to create a new comment.
     *
     * @param request type Comment
     * @return response entity object
     */
    @PostMapping()
    public ResponseEntity createComment(@Validated(GroupRequest.Create.class) @RequestBody CommentRequest request) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(request, comment);
        commentService.createComment(comment, request.getPostId());
        return createCommentResponse();
    }

    /**
     * Provide functionality to update comment
     *
     * @param request type Comment
     * @return response entity object
     */
    @PutMapping("/")
    public ResponseEntity updateComment(@Validated(GroupRequest.Create.class) @RequestBody CommentRequest request) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(request, comment);
        commentService.updateComment(comment);
        return updateCommentResponse();
    }

    /**
     * Provide functionality to delete comment
     *
     * @param commentId id of comment
     * @return response entity object
     */
    @DeleteMapping("/{commentId}")
    public ResponseEntity deleteComment(@PathVariable Long commentId) {
        if (commentId == null || commentId == 0) {
            return idMandatoryResponse();
        }
        commentService.deleteComment(commentId);
        return deleteCommentResponse();
    }

    private ResponseEntity getCommentByIdResponse(List<Comment> comments) {
        return ok(getResponse(
                messageRepository.RETRIEVE_COMMENT_SUCCESS_CODE,
                messageRepository.RETRIEVE_COMMENT_SUCCESS,
                comments
        ));
    }

    private ResponseEntity createCommentResponse() {
        return ok(getResponse(
                messageRepository.CREATE_COMMENT_SUCCESS_CODE,
                messageRepository.CREATE_COMMENT_SUCCESS,
                new ArrayList<>()
        ));
    }

    private ResponseEntity updateCommentResponse() {
        return ok(getResponse(
                messageRepository.UPDATE_COMMENT_SUCCESS_CODE,
                messageRepository.UPDATE_COMMENT_SUCCESS,
                new ArrayList<>()
        ));
    }

    private ResponseEntity deleteCommentResponse() {
        return ok(getResponse(
                messageRepository.DELETE_COMMENT_SUCCESS_CODE,
                messageRepository.DELETE_COMMENT_SUCCESS,
                new ArrayList<>()
        ));
    }

    private ResponseEntity idMandatoryResponse() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getResponse(
                messageRepository.COMMENT_ID_MANDATORY_CODE,
                messageRepository.COMMENT_ID_MANDATORY
        ));
    }
}
