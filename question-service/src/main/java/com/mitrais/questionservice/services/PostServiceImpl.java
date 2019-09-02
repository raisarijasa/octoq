package com.mitrais.questionservice.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.mitrais.questionservice.exceptions.model.DataNotFoundException;
import com.mitrais.questionservice.exceptions.model.ServiceException;
import com.mitrais.questionservice.models.Post;
import com.mitrais.questionservice.models.Status;
import com.mitrais.questionservice.models.Type;
import com.mitrais.questionservice.repositories.PostRepository;

/**
 * Provide implementation of functionality to manipulate Question and Answer input request.
 *
 * @author Rai Suardhyana Arijasa on 9/2/2019.
 */
@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepo;

    @Autowired
    public PostServiceImpl(PostRepository postRepo) {
        this.postRepo = postRepo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Post data) {
        postRepo.save(data);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Post> findById(Long id) {
        return postRepo.findById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(Long id) {
        postRepo.deleteById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Post> findAll() {
        return postRepo.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existsById(Long id) {
        return postRepo.existsById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Post getQuestionById(Long id) {
        Optional<Post> optPost = findById(id);
        if (optPost.isPresent()) {
            return optPost.get();
        } else {
            throw new DataNotFoundException("Question not found");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Post> getQuestions() {
        List<Post> questions = postRepo.findByTypeAndStatus(Type.QUESTION, Status.APPROVED);
        return postRepo.findByTypeAndStatus(Type.QUESTION, Status.APPROVED);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createQuestion(Post body) {
        body.setId(null)
                .setType(Type.QUESTION)
                .setStatus(Status.APPROVED)
                .setCreatedDate(new Date())
                .setComments(null)
                .setRates(null)
                .setAnswers(null);
        try {
            postRepo.save(body);
        } catch (DataIntegrityViolationException e) {
            throw new ServiceException("Question with id " + body.getId() + " already exist.");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateQuestion(Post body) {
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
     * {@inheritDoc}
     */
    @Override
    public void deleteQuestionById(Long id) {
        deleteById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void changeStatus(Long id, Status status) {
        Optional<Post> optPost = findById(id);
        if (optPost.isPresent()) {
            optPost.get().setStatus(status);
            save(optPost.get());
        } else {
            throw new DataNotFoundException("Question Not Found");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Post getAnswerById(Long id) {
        Optional<Post> optQuestion = findById(id);
        if (optQuestion.isPresent()) {
            return optQuestion.get();
        } else {
            throw new DataNotFoundException("Answer Not Found");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createAnswer(Post body, Long questionId) {
        findById(questionId)
                .map(question -> {
                    Set<Post> answers = question.getAnswers();
                    body.setId(null)
                            .setCreatedDate(new Date())
                            .setModifiedDate(null)
                            .setType(Type.ANSWER)
                            .setStatus(Status.APPROVED);
                    answers.add(body);
                    question.setAnswers(answers);
                    save(body);
                    return question;
                }).orElseThrow(() -> new DataNotFoundException("Question not found"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateAnswer(Post body) {
        findById(body.getId())
                .map(answer -> {
                    answer.setDescription(body.getDescription())
                            .setModifiedDate(new Date());
                    save(answer);
                    return answer;
                }).orElseThrow(() -> new DataNotFoundException("Answer not found!"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAnswer(Long answerId) {
        Optional<Post> optAnswer = findById(answerId);
        if (optAnswer.isPresent() && optAnswer.get().getType() == Type.ANSWER) {
            deleteById(answerId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Answer Not Found");
        }
    }
}
