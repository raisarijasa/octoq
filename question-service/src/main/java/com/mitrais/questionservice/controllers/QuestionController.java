package com.mitrais.questionservice.controllers;

import com.mitrais.questionservice.models.Question;
import com.mitrais.questionservice.models.Response;
import com.mitrais.questionservice.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api")
public class QuestionController implements BaseController<Question> {

    @Autowired
    private QuestionService questionService;

    @PostMapping("/question")
    public ResponseEntity createQuestion(@RequestBody Question question) {
        if (question.getId() != null || question.getId().equals(0)) {
            question.setId(null);
        }

        questionService.save(question);
        return ok(getResponse(
                false,
                "00001",
                "A new question has been created successfully",
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
