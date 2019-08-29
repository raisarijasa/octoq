package com.mitrais.questionservice.controllers;

import com.mitrais.questionservice.controllers.requests.QuestionRequest;
import com.mitrais.questionservice.dto.QuestionDto;
import com.mitrais.questionservice.exceptions.model.ServiceException;
import com.mitrais.questionservice.services.PostService;
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
 * Question controller
 */
@RestController
@RequestMapping("/api")
public class QuestionController extends BaseController<QuestionDto> {
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

        List<QuestionDto> questions = new ArrayList<>();
        questions.add(postService.getQuestionById(id));
        return ok(getResponse(
                false,
                "00003",
                "Retrieve data success",
                questions
        ));
    }

    /**
     * retrieve questions
     *
     * @return response entity object
     */
    @GetMapping("/question")
    public ResponseEntity getQuestions() {
        List<QuestionDto> questions = postService.getQuestions();
        return ok(getResponse(
                false,
                "00003",
                "Retrieve data success",
                questions
        ));
    }

    /**
     * create question
     *
     * @param body type QuestionDto
     * @return response entity object
     */
    @PostMapping("/question")
    public ResponseEntity createQuestion(@Valid @RequestBody QuestionRequest body) {
        QuestionDto data = new QuestionDto();
        BeanUtils.copyProperties(body, data);
        postService.createQuestion(data);
        return ok(getResponse(
                false,
                "00001",
                "A new question has been created successfully",
                new ArrayList<>()
        ));
    }

    /**
     * update question
     *
     * @param body type QuestionDto
     * @return response entity object
     */
    @PutMapping("/question")
    public ResponseEntity updateQuestion(@Valid @RequestBody QuestionRequest body) {
        QuestionDto data = new QuestionDto();
        BeanUtils.copyProperties(body, data);
        postService.updateQuestion(data);
        return ok(getResponse(
                false,
                "00002",
                "The question has been updated successfully",
                new ArrayList<>()
        ));
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
        postService.deleteQuestionById(id);

        return ok(getResponse(
                false,
                "00004",
                "Question has been deleted successfully",
                new ArrayList<>()
        ));
    }

    /**
     * change question status
     *
     * @param body type QuestionDto
     * @return
     */
    @PostMapping("/question/change_status")
    public ResponseEntity changeStatus(@RequestBody QuestionRequest body) {
        QuestionDto data = new QuestionDto();
        BeanUtils.copyProperties(body, data);
        postService.changeStatus(data);
        return ok(getResponse(
                false,
                "00002",
                "The question has been updated successfully",
                new ArrayList<>()
        ));
    }
}
