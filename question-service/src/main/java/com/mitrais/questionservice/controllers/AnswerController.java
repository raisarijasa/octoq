package com.mitrais.questionservice.controllers;

import com.mitrais.questionservice.controllers.requests.AnswerRequest;
import com.mitrais.questionservice.dto.AnswerDto;
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
 * Answer Controller
 */
@RestController
@RequestMapping("/api")
public class AnswerController extends BaseController<AnswerDto> {
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
        List<AnswerDto> answers = new ArrayList<>();
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
     * @param body type AnswerDto
     * @return response entity object
     */
    @PostMapping("/answer")
    public ResponseEntity createAnswer(@Valid @RequestBody AnswerRequest body) {
        AnswerDto dto = new AnswerDto();
        BeanUtils.copyProperties(body, dto);
        postService.createAnswer(dto, body.getQuestionId());
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
     * @param body type AnswerDto
     * @return response entity object
     */
    @PutMapping("/answer")
    public ResponseEntity updateAnswer(@Valid @RequestBody AnswerRequest body) {
        if (body.getId() == null || body.getId() == 0) {
            throw new ServiceException("Question ID should not be null or 0");
        }
        AnswerDto dto = new AnswerDto();
        BeanUtils.copyProperties(body, dto);
        postService.updateAnswer(dto);
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
     * @param questionId type Long
     * @param answerId   type Long
     * @return response entity object
     */
    @DeleteMapping("/question/{questionId}/answer/{answerId}")
    public ResponseEntity deleteAnswer(@PathVariable Long questionId,
                                       @PathVariable Long answerId) {
        postService.deleteAnswer(questionId, answerId);
        return ok(getResponse(
                false,
                "00004",
                "Answer has been deleted successfully",
                new ArrayList<>()
        ));
    }
}
