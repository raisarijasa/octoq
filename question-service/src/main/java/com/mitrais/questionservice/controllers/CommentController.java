package com.mitrais.questionservice.controllers;

import com.mitrais.questionservice.dto.CommentDto;
import com.mitrais.questionservice.models.Comment;
import com.mitrais.questionservice.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

/**
 * Comment Controller
 */
@RestController
@RequestMapping("/api")
public class CommentController implements BaseController<Comment> {
    private CommentService commentService;

    /**
     * Comment Controller Constructor
     *
     * @param commentService service of comment
     */
    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    /**
     * Get Comment by Id
     *
     * @param commentId id of comment
     * @return response entity object
     */
    @GetMapping("/comment/{commentId}")
    public ResponseEntity getCommentById(@PathVariable Long commentId) {
        if (commentId == null || commentId == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request");
        }
        return commentService.getCommentById(commentId);
    }

    /**
     * create a comment
     *
     * @param body type CommentDto
     * @return response entity object
     */
    @PostMapping("/comment")
    public ResponseEntity createComment(@Valid @RequestBody CommentDto body) {
        return commentService.createComment(body);
    }

    /**
     * update a comment
     *
     * @param body type CommentDto
     * @return response entity object
     */
    @PutMapping("/comment")
    public ResponseEntity updateComment(@Valid @RequestBody CommentDto body) {
        return commentService.updateComment(body);
    }

    /**
     * delete comment by id
     *
     * @param commentId id of comment
     * @return response entity object
     */
    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity deleteComment(@PathVariable Long commentId) {
        return commentService.deleteComment(commentId);
    }
}
