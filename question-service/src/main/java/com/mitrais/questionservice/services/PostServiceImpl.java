package com.mitrais.questionservice.services;

import com.mitrais.questionservice.dto.AnswerDto;
import com.mitrais.questionservice.dto.QuestionDto;
import com.mitrais.questionservice.exceptions.model.DataNotFoundException;
import com.mitrais.questionservice.exceptions.model.ServiceException;
import com.mitrais.questionservice.models.Post;
import com.mitrais.questionservice.models.Status;
import com.mitrais.questionservice.models.Type;
import com.mitrais.questionservice.repositories.PostRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

/**
 * Post service
 */
@Service
public class PostServiceImpl implements PostService {
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
    public QuestionDto getQuestionById(Long id) {
        if (id == null || id == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request");
        }
        Optional<Post> optPost = findById(id);
        if (optPost.isPresent()) {
            QuestionDto dto = new QuestionDto();
            BeanUtils.copyProperties(optPost.get(), dto);
            return dto;
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
    public List<QuestionDto> getQuestions() {
        List<Post> questions = findAll();
        List<QuestionDto> questionDtos = new ArrayList<>();
        QuestionDto dto;
        for (Post post : questions) {
            dto = new QuestionDto();
            BeanUtils.copyProperties(post, dto, "answers");
            questionDtos.add(dto);
        }
        return questionDtos;
    }

    /**
     * create new question
     *
     * @param body type QuestionDto
     * @return response entity object
     */
    @Override
    public void createQuestion(QuestionDto body) {
        Post post = new Post().setUserId(body.getUserId())
                .setTitle(body.getTitle())
                .setDescription(body.getDescription())
                .setType(Type.QUESTION)
                .setStatus(Status.APPROVED)
                .setCreatedDate(new Date())
                .setAnswers(null);
        try {
            postRepo.save(post);
        } catch (DataIntegrityViolationException e) {
            throw new ServiceException("Question with id " + body.getId() + " already exist.");
        }

    }

    /**
     * update question
     *
     * @param body QuestionDto
     * @return response entity
     */
    @Override
    public void updateQuestion(QuestionDto body) {
        Optional<Post> optPost = findById(body.getId());
        if (optPost.isPresent()) {
            Post post = optPost.get()
                    .setTitle(body.getTitle())
                    .setDescription(body.getDescription())
                    .setModifiedDate(new Date());
            postRepo.save(post);
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
    public void deleteQuestionById(Long id) {
        deleteById(id);
    }

    /**
     * change status
     *
     * @param body QuestionDto
     * @return response entity
     */
    @Override
    public void changeStatus(QuestionDto body) {
        Optional<Post> optPost = findById(body.getId());
        if (optPost.isPresent()) {
            optPost.get().setStatus(body.getStatus());
            save(optPost.get());
        } else {
            throw new DataNotFoundException("Question Not Found");
        }
    }

    /**
     * get answer by Id
     *
     * @param id of answer
     * @return Answer Dto
     */
    @Override
    public AnswerDto getAnswerById(Long id) {
        AnswerDto dto = new AnswerDto();
        Optional<Post> optQuestion = findById(id);
        if (optQuestion.isPresent()) {
            BeanUtils.copyProperties(optQuestion.get(), dto);
            return dto;
        } else {
            throw new DataNotFoundException("Answer Not Found");
        }
    }

    /**
     * create new answer
     *
     * @param body AnswerDto
     */
    @Override
    public void createAnswer(AnswerDto body, Long questionId) {
        findById(questionId)
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
    }

    /**
     * update answer
     *
     * @param body AnswerDto
     */
    @Override
    public void updateAnswer(AnswerDto body) {
        findById(body.getId())
                .map(answer -> {
                    answer.setDescription(body.getDescription())
                            .setModifiedDate(new Date());
                    save(answer);
                    return answer;
                }).orElseThrow(() -> new DataNotFoundException("Answer not found!"));
    }

    /**
     * delete answer
     *
     * @param questionId of question
     * @param answerId   of answer
     */
    @Override
    public void deleteAnswer(Long questionId, Long answerId) {
        Optional<Post> optAnswer = findById(answerId);
        if (optAnswer.isPresent() && optAnswer.get().getType() == Type.ANSWER) {
            deleteById(answerId);
        } else {
            if (optAnswer.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Question Not Found");
            }

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Answer Not Found");
        }
    }
}
