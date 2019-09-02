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

import com.mitrais.questionservice.controllers.requests.QuestionRequest;
import com.mitrais.questionservice.controllers.requests.StatusRequest;
import com.mitrais.questionservice.exceptions.model.ServiceException;
import com.mitrais.questionservice.models.Post;
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
    @GetMapping("/{id}")
    public ResponseEntity getQuestionById(@PathVariable Long id) {
        if (id == null || id == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Question ID should not be null or 0");
        }

        List<Post> questions = new ArrayList<>();
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
    @GetMapping("/")
    public ResponseEntity getQuestions() {
        List<Post> questions = postService.getQuestions();
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
     * @param request type QuestionDto
     * @return response entity object
     */
    @PostMapping("/")
    public ResponseEntity createQuestion(@Validated(QuestionRequest.CreateGroup.class) @RequestBody QuestionRequest request) {
        Post data = new Post();
        BeanUtils.copyProperties(request, data);
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
     * @param request type QuestionDto
     * @return response entity object
     */
    @PutMapping("/")
    public ResponseEntity updateQuestion(@Validated(QuestionRequest.UpdateGroup.class) @RequestBody QuestionRequest request) {
        Post data = new Post();
        BeanUtils.copyProperties(request, data);
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
    @DeleteMapping("/{id}")
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
     * @param request StatusRequest Object
     * @return response entity object
     */
    @PostMapping("/change_status")
    public ResponseEntity changeStatus(@RequestBody StatusRequest request) {
        postService.changeStatus(request.getId(), request.getStatus());
        return ok(getResponse(
                false,
                "00002",
                "The question has been updated successfully",
                new ArrayList<>()
        ));
    }
}
