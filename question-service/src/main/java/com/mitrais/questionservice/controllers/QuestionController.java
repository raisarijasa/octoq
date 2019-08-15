package com.mitrais.questionservice.controllers;

import com.mitrais.questionservice.models.Question;
import com.mitrais.questionservice.models.Response;
import com.mitrais.questionservice.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api")
public class QuestionController implements BaseController<Question> {

    @Autowired
    private QuestionService questionService;

    @PostMapping("/question")
    public ResponseEntity createQuestion(@RequestBody Question question) {
        if (question.getId() != null) {
            question.setId(null);
        }
        question.setCreatedDate(new Date());
        question.setModifyDate(new Date());
        questionService.save(question);
        return ok(getResponse(
                false,
                "00001",
                "A new question has been created successfully",
                new ArrayList<>()
        ));
    }

    @PutMapping("/question")
    public ResponseEntity updateQuestion(@RequestBody Question question) {
        if (question.getId() == null || question.getId() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Question Not Found");
        }
        question.setModifyDate(new Date());
        questionService.save(question);
        return ok(getResponse(
                false,
                "00002",
                "The question has been updated successfully",
                new ArrayList<>()
        ));
    }

    @GetMapping("/question/{id}")
    public ResponseEntity getQuestionById(@PathVariable Long id) {
        if (id == null || id == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request");
        }
        Question question = questionService.findDataById(id);
        if (question == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Question Not Found");
        }
        List<Question> questions = new ArrayList<>();
        questions.add(question);
        return ok(getResponse(
                false,
                "00003",
                "Retrieve data success",
                questions
        ));
    }

    @GetMapping("/question")
    public ResponseEntity getQuestions() {
        List<Question> questions = questionService.findAll();
        if (questions == null || questions.size() < 1) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Question Not Found");
        }
        return ok(getResponse(
                false,
                "00003",
                "Retrieve data success",
                questions
        ));
    }

    @DeleteMapping("/question/{id}")
    public ResponseEntity deleteQuestionById(@PathVariable Long id) {
        if (id == null || id == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request");
        }
        questionService.deleteById(id);
        return ok(getResponse(
                false,
                "00004",
                "Question has been deleted successfully",
                new ArrayList<>()
        ));
    }

    @Override
    public Response<Question> getResponse(boolean error, String code, String message, List<Question> data) {
        Response<Question> response = new Response<>();
        response.setError(error);
        response.setCode(code);
        response.setMessage(message);
        response.setData(data);
        return response;
    }
}
