package com.mitrais.questionservice.services;

import com.mitrais.questionservice.dto.AnswerDto;
import com.mitrais.questionservice.dto.QuestionDto;
import com.mitrais.questionservice.exceptions.model.DataNotFoundException;
import com.mitrais.questionservice.models.Post;
import com.mitrais.questionservice.models.Status;
import com.mitrais.questionservice.models.Type;
import com.mitrais.questionservice.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

import static org.springframework.http.ResponseEntity.ok;

/**
 * Post service
 */
@Service
public class PostServiceImpl extends BaseServiceImpl<Post> implements PostService {
    private PostRepository postRepo;

    /**
     * Post service constructor
     *
     * @param postRepo post repository
     */
    @Autowired
    public PostServiceImpl(PostRepository postRepo) {
        this.postRepo = postRepo;
    }

    /**
     * save data
     *
     * @param data generic object
     */
    @Override
    public void save(Post data) {
        postRepo.save(data);
    }

    /**
     * find by id
     *
     * @param id id of data
     * @return optional Post
     */
    @Override
    public Optional<Post> findById(Long id) {
        return postRepo.findById(id);
    }

    /**
     * delete by id
     *
     * @param id of data
     */
    @Override
    public void deleteById(Long id) {
        postRepo.deleteById(id);
    }

    /**
     * find all it will filter by type and status
     *
     * @return list of post
     */
    @Override
    public List<Post> findAll() {
        return postRepo.findByTypeAndStatus(Type.QUESTION, Status.APPROVED);
    }

    /**
     * validate data exist
     *
     * @param id id of data
     * @return boolean
     */
    @Override
    public boolean existsById(Long id) {
        return postRepo.existsById(id);
    }

    /**
     * get question by id
     *
     * @param id of question
     * @return response entity object
     */
    @Override
    public ResponseEntity getQuestionById(Long id) {
        if (id == null || id == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request");
        }
        Optional<Post> optPost = findById(id);
        if (optPost.isPresent()) {
            List<Post> posts = new ArrayList<>();
            posts.add(optPost.get());
            return ok(getResponse(
                    false,
                    "00003",
                    "Retrieve data success",
                    posts
            ));
        } else {
            throw new DataNotFoundException("Question not found");
        }
    }

    /**
     * get questions
     *
     * @return response entity object
     */
    @Override
    public ResponseEntity getQuestions() {
        List<Post> questions = findAll();
        return ok(getResponse(
                false,
                "00003",
                "Retrieve data success",
                questions
        ));
    }

    /**
     * create new question
     *
     * @param body type QuestionDto
     * @return response entity object
     */
    @Override
    public ResponseEntity createQuestion(QuestionDto body) {
        Post post = new Post().setUserId(body.getUserId())
                .setTitle(body.getTitle())
                .setDescription(body.getDescription())
                .setType(Type.QUESTION)
                .setStatus(Status.APPROVED)
                .setCreatedDate(new Date())
                .setAnswers(null);
        postRepo.save(post);
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
     * @param body QuestionDto
     * @return response entity
     */
    @Override
    public ResponseEntity updateQuestion(QuestionDto body) {
        Optional<Post> optPost = findById(body.getId());
        if (optPost.isPresent()) {
            Post post = optPost.get()
                    .setTitle(body.getTitle())
                    .setDescription(body.getDescription())
                    .setModifiedDate(new Date());
            postRepo.save(post);
            return ok(getResponse(
                    false,
                    "00002",
                    "The question has been updated successfully",
                    new ArrayList<>()
            ));
        } else {
            throw new DataNotFoundException("Question Not Found");
        }
    }

    /**
     * delete question by id
     *
     * @param id of question
     * @return response entity object
     */
    @Override
    public ResponseEntity deleteQuestionById(Long id) {
        deleteById(id);
        return ok(getResponse(
                false,
                "00004",
                "Question has been deleted successfully",
                new ArrayList<>()
        ));
    }

    /**
     * change status
     *
     * @param body QuestionDto
     * @return response entity
     */
    @Override
    public ResponseEntity changeStatus(QuestionDto body) {
        Optional<Post> optPost = findById(body.getId());
        if (optPost.isPresent()) {
            optPost.get().setStatus(body.getStatus());
            save(optPost.get());
            return ok(getResponse(
                    false,
                    "00002",
                    "The question has been updated successfully",
                    new ArrayList<>()
            ));
        } else {
            throw new DataNotFoundException("Question Not Found");
        }
    }

    /**
     * get answer by Id
     *
     * @param id of answer
     * @return response entity
     */
    @Override
    public ResponseEntity getAnswerById(Long id) {
        List<Post> answers = new ArrayList<>();
        Optional<Post> optQuestion = findById(id);
        optQuestion.ifPresent(answers::add);
        return ok(getResponse(
                false,
                "00003",
                "Retrieve data success",
                answers
        ));
    }

    /**
     * create new answer
     *
     * @param body AnswerDto
     * @return response entity object
     */
    @Override
    public ResponseEntity createAnswer(AnswerDto body) {
        findById(body.getQuestionId())
                .map(question -> {
                    Set<Post> answers = question.getAnswers();
                    Post answer = new Post()
                            .setUserId(body.getUserId())
                            .setDescription(body.getDescription())
                            .setCreatedDate(new Date())
                            .setModifiedDate(null)
                            .setType(Type.ANSWER)
                            .setStatus(Status.APPROVED);
                    answers.add(answer);
                    question.setAnswers(answers);
                    save(answer);
                    return question;
                }).orElseThrow(() -> new DataNotFoundException("Question not found"));


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
     * @param body AnswerDto
     * @return response entity object
     */
    @Override
    public ResponseEntity updateAnswer(AnswerDto body) {
        if (!existsById(body.getQuestionId())) {
            throw new DataNotFoundException("Question not found!");
        }

        findById(body.getId())
                .map(answer -> {
                    answer.setDescription(body.getDescription())
                            .setModifiedDate(new Date());
                    save(answer);
                    return answer;
                }).orElseThrow(() -> new DataNotFoundException("Answer not found!"));

        return ok(getResponse(
                false,
                "00002",
                "The answer has been updated successfully",
                new ArrayList<>()
        ));
    }

    /**
     * delete answer
     *
     * @param questionId of question
     * @param answerId   of answer
     * @return response entity
     */
    @Override
    public ResponseEntity deleteAnswer(Long questionId, Long answerId) {
        Optional<Post> optAnswer = findById(answerId);
        if (optAnswer.isPresent() && optAnswer.get().getType() == Type.ANSWER) {
            deleteById(answerId);
            return ok(getResponse(
                    false,
                    "00004",
                    "Answer has been deleted successfully",
                    new ArrayList<>()
            ));
        } else {
            if (optAnswer.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Question Not Found");
            }

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Answer Not Found");
        }
    }
}
