package com.mitrais.questionservice.controllers;

import com.mitrais.questionservice.dto.AnswerDto;
import com.mitrais.questionservice.models.Post;
import com.mitrais.questionservice.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

/**
 * Answer Controller
 */
@RestController
@RequestMapping("/api")
public class AnswerController implements BaseController<Post> {
    private PostService postService;

    /**
     * Answer Controller Constructor
     *
     * @param postService answer service
     */
    @Autowired
    public AnswerController(PostService postService) {
        this.postService = postService;
    }

    /**
     * Retrieve Answer by Answer Id
     *
     * @param answerId type Long
     * @return response entity object
     */
    @GetMapping("/answer/{answerId}")
    public ResponseEntity getAnswerById(@PathVariable Long answerId) {
        if (answerId == null || answerId == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request");
        }
        return postService.getAnswerById(answerId);
    }

    /**
     * create answer
     *
     * @param body type AnswerDto
     * @return response entity object
     */
    @PostMapping("/answer")
    public ResponseEntity createAnswer(@Valid @RequestBody AnswerDto body) {
        return postService.createAnswer(body);
    }

    /**
     * update answer
     *
     * @param body type AnswerDto
     * @return response entity object
     */
    @PutMapping("/answer")
    public ResponseEntity updateAnswer(@Valid @RequestBody AnswerDto body) {
        return postService.updateAnswer(body);
    }

    /**
     * Delete answer
     *
     * @param questionId type Long
     * @param answerId   type Long
     * @return response entity object
     */
    @DeleteMapping("/question/{questionId}/answer/{answerId}")
    public ResponseEntity deleteAnswer(@PathVariable Long questionId,
                                       @PathVariable Long answerId) {
        return postService.deleteAnswer(questionId, answerId);
    }
}
