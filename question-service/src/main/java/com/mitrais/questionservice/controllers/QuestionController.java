package com.mitrais.questionservice.controllers;

import com.mitrais.questionservice.dto.QuestionDto;
import com.mitrais.questionservice.exceptions.model.ServiceException;
import com.mitrais.questionservice.models.Post;
import com.mitrais.questionservice.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

/**
 * Question controller
 */
@RestController
@RequestMapping("/api")
public class QuestionController implements BaseController<Post> {
    private PostService postService;

    /**
     * Question controller constructor
     *
     * @param postService service of question
     */
    @Autowired
    public QuestionController(PostService postService) {
        this.postService = postService;
    }

    /**
     * get question by Id
     *
     * @param id id of the question
     * @return response entity object
     */
    @GetMapping("/question/{id}")
    public ResponseEntity getQuestionById(@PathVariable Long id) {
        if (id == null || id == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Question ID should not be null or 0");
        }

        return postService.getQuestionById(id);
    }

    /**
     * retrieve questions
     *
     * @return response entity object
     */
    @GetMapping("/question")
    public ResponseEntity getQuestions() {
        return postService.getQuestions();
    }

    /**
     * create question
     *
     * @param body type QuestionDto
     * @return response entity object
     */
    @PostMapping("/question")
    public ResponseEntity createQuestion(@Valid @RequestBody QuestionDto body) {
        return postService.createQuestion(body);
    }

    /**
     * update question
     *
     * @param body type QuestionDto
     * @return response entity object
     */
    @PutMapping("/question")
    public ResponseEntity updateQuestion(@Valid @RequestBody QuestionDto body) {
        return postService.updateQuestion(body);
    }

    /**
     * delete question by id
     *
     * @param id of question
     * @return response entity object
     */
    @DeleteMapping("/question/{id}")
    public ResponseEntity deleteQuestionById(@PathVariable Long id) {
        if (id == null || id == 0) {
            throw new ServiceException("Question ID should not be null or 0");
        }
        return postService.deleteQuestionById(id);
    }

    /**
     * change question status
     *
     * @param body type QuestionDto
     * @return
     */
    @PostMapping("/question/change_status")
    public ResponseEntity changeStatus(@RequestBody QuestionDto body) {
        return postService.changeStatus(body);
    }
}
