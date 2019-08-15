package com.mitrais.questionservice.controllers;

import com.mitrais.questionservice.models.Answer;
import com.mitrais.questionservice.models.Question;
import com.mitrais.questionservice.models.Response;
import com.mitrais.questionservice.services.AnswerService;
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
public class AnswerController implements BaseController<Answer> {

    @Autowired
    private AnswerService answerService;

    @PostMapping("/answer")
    public ResponseEntity createAnswer(@RequestBody Answer answer) {
        if (answer.getId() != null) {
            answer.setId(null);
        }
        answer.setCreatedDate(new Date());
        answer.setModifyDate(null);
        answerService.save(answer);
        return ok(getResponse(
                false,
                "00001",
                "A new answer has been created successfully",
                new ArrayList<>()
        ));
    }

    @PutMapping("/answer")
    public ResponseEntity updateAnswer(@RequestBody Answer answer) {
        if (answer.getId() == null || answer.getId() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data Not Found");
        }
        answer.setModifyDate(new Date());
        answerService.save(answer);
        return ok(getResponse(
                false,
                "00002",
                "The answer has been updated successfully",
                new ArrayList<>()
        ));
    }

    @GetMapping("/answer/{id}")
    public ResponseEntity getAnswerById(@PathVariable Long id) {
        if (id == null || id == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request");
        }
        Answer answer = answerService.findDataById(id);
        if (answer == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Answer Not Found");
        }
        List<Answer> answers = new ArrayList<>();
        answers.add(answer);
        return ok(getResponse(
                false,
                "00003",
                "Retrieve data success",
                answers
        ));
    }

//    @GetMapping("/answer")
//    public ResponseEntity getAnswerByQuestionId(@RequestParam("question") Long questionId) {
//        List<Answer> answers = answerService.findByQuestionId(questionId);
//        if (answers == null || answers.size() < 1) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data Not Found");
//        }
//        return ok(getResponse(
//                false,
//                "00003",
//                "Retrieve data success",
//                answers
//        ));
//    }

    @DeleteMapping("/answer/{id}")
    public ResponseEntity deleteAnswerById(@PathVariable Long id) {
        if (id == null || id == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request");
        }
        answerService.deleteById(id);
        return ok(getResponse(
                false,
                "00004",
                "Answer has been deleted successfully",
                new ArrayList<>()
        ));
    }

    @Override
    public Response<Answer> getResponse(boolean error, String code, String message, List<Answer> data) {
        return null;
    }
}
