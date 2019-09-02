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

import com.mitrais.questionservice.controllers.requests.AnswerRequest;
import com.mitrais.questionservice.exceptions.model.ServiceException;
import com.mitrais.questionservice.models.Post;
import com.mitrais.questionservice.services.PostService;

import static org.springframework.http.ResponseEntity.ok;

/**
 * Provide functionality to manipulate Answer request data and return response entity.
 *
 * @author Rai Suardhyana Arijasa on 9/2/2019.
 */
@RestController
@RequestMapping("/answers")
public class AnswerController extends BaseController<Post> {
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
    @GetMapping("/{answerId}")
    public ResponseEntity getAnswerById(@PathVariable Long answerId) {
        if (answerId == null || answerId == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request");
        }
        List<Post> answers = new ArrayList<>();
        answers.add(postService.getAnswerById(answerId));
        return ok(getResponse(
                false,
                "00003",
                "Retrieve data success",
                answers
        ));
    }

    /**
     * create answer
     *
     * @param request type AnswerRequest
     * @return response entity object
     */
    @PostMapping("/{questionId}")
    public ResponseEntity createAnswer(@Validated(AnswerRequest.CreateGroup.class) @RequestBody AnswerRequest request) {
        Post post = new Post();
        BeanUtils.copyProperties(request, post);
        postService.createAnswer(post, request.getQuestionId());
        return ok(getResponse(
                false,
                "00001",
                "A new answer has been created successfully",
                new ArrayList<>()
        ));
    }

    /**
     * update answer
     *
     * @param request type AnswerDto
     * @return response entity object
     */
    @PutMapping("/")
    public ResponseEntity updateAnswer(@Validated(AnswerRequest.UpdateGroup.class) @RequestBody AnswerRequest request) {
        if (request.getId() == null || request.getId() == 0) {
            throw new ServiceException("Question ID should not be null or 0");
        }
        Post post = new Post();
        BeanUtils.copyProperties(request, post);
        postService.updateAnswer(post);
        return ok(getResponse(
                false,
                "00002",
                "The answer has been updated successfully",
                new ArrayList<>()
        ));
    }

    /**
     * Delete answer
     *
     * @param answerId type Long
     * @return response entity object
     */
    @DeleteMapping("/{answerId}")
    public ResponseEntity deleteAnswer(@PathVariable Long answerId) {
        postService.deleteAnswer(answerId);
        return ok(getResponse(
                false,
                "00004",
                "Answer has been deleted successfully",
                new ArrayList<>()
        ));
    }
}
