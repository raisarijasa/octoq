package com.mitrais.questionservice.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.mitrais.questionservice.controllers.requests.AnswerRequest;
import com.mitrais.questionservice.controllers.requests.GroupRequest;
import com.mitrais.questionservice.models.Post;
import com.mitrais.questionservice.repositories.MessageRepository;
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
    private final PostService postService;
    private final MessageRepository messageRepository;

    @Autowired
    public AnswerController(PostService postService, MessageRepository messageRepository) {
        this.postService = postService;
        this.messageRepository = messageRepository;
    }

    /**
     * Provide functionality to retrieve answer by id.
     *
     * @param answerId type Long
     * @return response entity object
     */
    @GetMapping("/{answerId}")
    public ResponseEntity getAnswerById(@PathVariable Long answerId) {
        if (answerId == null || answerId == 0) {
            return idMandatoryResponse();
        }
        List<Post> answers = new ArrayList<>();
        answers.add(postService.getAnswerById(answerId));
        return getAnswerByIdResponse(answers);
    }

    /**
     * Provide functionality to create new answer.
     *
     * @param request type AnswerRequest
     * @return response entity object
     */
    @PostMapping("/{questionId}")
    public ResponseEntity createAnswer(@Validated(GroupRequest.Create.class) @RequestBody AnswerRequest request) {
        Post post = new Post();
        BeanUtils.copyProperties(request, post);
        postService.createAnswer(post, request.getQuestionId());
        return createAnswerResponse();
    }

    /**
     * Provide functionality to update answer.
     *
     * @param request type AnswerRequest
     * @return response entity object
     */
    @PutMapping("/")
    public ResponseEntity updateAnswer(@Validated(GroupRequest.Update.class) @RequestBody AnswerRequest request) {
        if (request.getId() == null || request.getId() == 0) {
            return idMandatoryResponse();
        }
        Post post = new Post();
        BeanUtils.copyProperties(request, post);
        postService.updateAnswer(post);
        return updateAnswerResponse();
    }

    /**
     * Provide functionality to delete answer.
     *
     * @param answerId type Long
     * @return response entity object
     */
    @DeleteMapping("/{answerId}")
    public ResponseEntity deleteAnswer(@PathVariable Long answerId) {
        postService.deleteAnswer(answerId);
        return deleteAnswerResponse();
    }

    private ResponseEntity getAnswerByIdResponse(List<Post> answers) {
        return ok(getResponse(
                messageRepository.RETRIEVE_ANSWER_SUCCESS_CODE,
                messageRepository.RETRIEVE_ANSWER_SUCCESS,
                answers
        ));
    }

    private ResponseEntity createAnswerResponse() {
        return ok(getResponse(
                messageRepository.CREATE_ANSWER_SUCCESS_CODE,
                messageRepository.CREATE_ANSWER_SUCCESS,
                new ArrayList<>()
        ));
    }

    private ResponseEntity updateAnswerResponse() {
        return ok(getResponse(
                messageRepository.UPDATE_ANSWER_SUCCESS_CODE,
                messageRepository.UPDATE_ANSWER_SUCCESS,
                new ArrayList<>()
        ));
    }

    private ResponseEntity deleteAnswerResponse() {
        return ok(getResponse(
                messageRepository.DELETE_ANSWER_SUCCESS_CODE,
                messageRepository.DELETE_ANSWER_SUCCESS,
                new ArrayList<>()
        ));
    }

    private ResponseEntity idMandatoryResponse() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getResponse(
                messageRepository.ANSWER_ID_MANDATORY_CODE,
                messageRepository.ANSWER_ID_MANDATORY
        ));
    }
}
