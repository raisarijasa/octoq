package com.mitrais.questionservice.controllers;

import com.mitrais.questionservice.controllers.requests.CommentRequest;
import com.mitrais.questionservice.dto.CommentDto;
import com.mitrais.questionservice.services.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

/**
 * Comment Controller
 */
@RestController
@RequestMapping("/comments")
public class CommentController extends BaseController<CommentDto> {
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
        List<CommentDto> comments = new ArrayList<>();
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
     * @param body type CommentDto
     * @return response entity object
     */
    @PostMapping("/")
    public ResponseEntity createComment(@Valid @RequestBody CommentRequest body) {
        CommentDto dto = new CommentDto();
        BeanUtils.copyProperties(body, dto);
        commentService.createComment(dto, body.getPostId());
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
     * @param body type CommentDto
     * @return response entity object
     */
    @PutMapping("/")
    public ResponseEntity updateComment(@Valid @RequestBody CommentRequest body) {
        CommentDto dto = new CommentDto();
        BeanUtils.copyProperties(body, dto);
        commentService.updateComment(dto);
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
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request");
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
