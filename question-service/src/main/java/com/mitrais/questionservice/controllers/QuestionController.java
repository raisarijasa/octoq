package com.mitrais.questionservice.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.mitrais.questionservice.controllers.requests.GroupRequest;
import com.mitrais.questionservice.controllers.requests.QuestionRequest;
import com.mitrais.questionservice.controllers.requests.StatusRequest;
import com.mitrais.questionservice.models.Post;
import com.mitrais.questionservice.repositories.MessageRepository;
import com.mitrais.questionservice.services.PostService;

import static org.springframework.http.ResponseEntity.ok;

/**
 * Provide functionality to manipulate Question data request.
 *
 * @author Rai Suardhyana Arijasa on 9/2/2019.
 */
@RestController
@RequestMapping("/questions")
public class QuestionController extends BaseController<Post> {
    private final PostService postService;
    private final MessageRepository messageRepository;

    @Autowired
    public QuestionController(PostService postService, MessageRepository messageRepository) {
        this.postService = postService;
        this.messageRepository = messageRepository;
    }

    /**
     * Provide functionality to retrieve question by id.
     *
     * @param id id of the question
     * @return response entity object
     */
    @GetMapping("/{id}")
    public ResponseEntity getQuestionById(@PathVariable Long id) {
        if (id == null || id == 0) {
            return idMandatoryResponse();
        }

        List<Post> questions = new ArrayList<>();
        questions.add(postService.getQuestionById(id));
        return getQuestionResponse(questions);
    }

    /**
     * Provide functionality to retrieve questions.
     *
     * @return response entity object
     */
    @GetMapping()
    public ResponseEntity getQuestions() {
        List<Post> questions = postService.getQuestions();
        return getQuestionResponse(questions);
    }

    /**
     * Provide functionality to create a new question.
     *
     * @param request type QuestionRequest
     * @return response entity object
     */
    @PostMapping()
    public ResponseEntity createQuestion(@Validated(GroupRequest.Create.class) @RequestBody QuestionRequest request) {
        Post data = new Post();
        BeanUtils.copyProperties(request, data);
        postService.createQuestion(data);
        return createQuestionResponse();
    }

    /**
     * Provide functionality to update question.
     *
     * @param request type QuestionRequest
     * @return response entity object
     */
    @PutMapping()
    public ResponseEntity updateQuestion(@Validated(GroupRequest.Update.class) @RequestBody QuestionRequest request) {
        Post data = new Post();
        BeanUtils.copyProperties(request, data);
        postService.updateQuestion(data);
        return updateQuestionResponse();
    }

    /**
     * Provide functionality to delete question by id.
     *
     * @param id of question
     * @return response entity object
     */
    @DeleteMapping("/{id}")
    public ResponseEntity deleteQuestionById(@PathVariable Long id) {
        if (id == null || id == 0) {
            return idMandatoryResponse();
        }
        postService.deleteQuestionById(id);

        return deleteQuestionResponse();
    }

    /**
     * Provide functionality to change question status.
     *
     * @param request StatusRequest Object
     * @return response entity object
     */
    @PostMapping("/change_status")
    public ResponseEntity changeStatus(@RequestBody StatusRequest request) {
        postService.changeStatus(request.getId(), request.getStatus());
        return updateQuestionResponse();
    }

    private ResponseEntity getQuestionResponse(List<Post> answers) {
        return ok(getResponse(
                messageRepository.RETRIEVE_QUESTION_SUCCESS_CODE,
                messageRepository.RETRIEVE_QUESTION_SUCCESS,
                answers
        ));
    }

    private ResponseEntity createQuestionResponse() {
        return ok(getResponse(
                messageRepository.CREATE_QUESTION_SUCCESS_CODE,
                messageRepository.CREATE_QUESTION_SUCCESS,
                new ArrayList<>()
        ));
    }

    private ResponseEntity updateQuestionResponse() {
        return ok(getResponse(
                messageRepository.UPDATE_QUESTION_SUCCESS_CODE,
                messageRepository.UPDATE_QUESTION_SUCCESS,
                new ArrayList<>()
        ));
    }

    private ResponseEntity deleteQuestionResponse() {
        return ok(getResponse(
                messageRepository.DELETE_QUESTION_SUCCESS_CODE,
                messageRepository.DELETE_QUESTION_SUCCESS,
                new ArrayList<>()
        ));
    }

    private ResponseEntity idMandatoryResponse() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getResponse(
                messageRepository.QUESTION_ID_MANDATORY_CODE,
                messageRepository.QUESTION_ID_MANDATORY
        ));
    }
}
