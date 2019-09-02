package com.mitrais.questionservice.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.mitrais.questionservice.controllers.requests.CommentRequest;
import com.mitrais.questionservice.exceptions.model.ServiceException;
import com.mitrais.questionservice.models.Comment;
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
    @GetMapping("/{commentId}")
    public ResponseEntity getCommentById(@PathVariable Long commentId) {
        if (commentId == null || commentId == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request");
        }
        List<Comment> comments = new ArrayList<>();
        comments.add(commentService.getCommentById(commentId));
        return ok(getResponse(
                false,
                "00003",
                "Retrieve data success",
                comments
        ));
    }

    /**
     * create a comment
     *
     * @param request type Comment
     * @return response entity object
     */
    @PostMapping("/")
    public ResponseEntity createComment(@Validated(CommentRequest.CreateGroup.class) @RequestBody CommentRequest request) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(request, comment);
        commentService.createComment(comment, request.getPostId());
        return ok(getResponse(
                false,
                "00001",
                "A new comment has been created successfully",
                new ArrayList<>()
        ));
    }

    /**
     * update a comment
     *
     * @param request type Comment
     * @return response entity object
     */
    @PutMapping("/")
    public ResponseEntity updateComment(@Validated(CommentRequest.CreateGroup.class) @RequestBody CommentRequest request) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(request, comment);
        commentService.updateComment(comment);
        return ok(getResponse(
                false,
                "00002",
                "The comment has been updated successfully",
                new ArrayList<>()
        ));
    }

    /**
     * delete comment by id
     *
     * @param commentId id of comment
     * @return response entity object
     */
    @DeleteMapping("/{commentId}")
    public ResponseEntity deleteComment(@PathVariable Long commentId) {
        if (commentId == null || commentId == 0) {
            throw new ServiceException("Comment Id is mandatory.");
        }
        commentService.deleteComment(commentId);
        return ok(getResponse(
                false,
                "00004",
                "Comment has been deleted successfully",
                new ArrayList<>()
        ));
    }
}
