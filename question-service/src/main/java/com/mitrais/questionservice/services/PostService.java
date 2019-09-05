package com.mitrais.questionservice.services;

import java.util.List;

import com.mitrais.questionservice.models.Post;
import com.mitrais.questionservice.models.Status;

/**
 * Provide functionality to manipulate Question and Answer input request.
 *
 * @author Rai Suardhyana Arijasa on 9/2/2019.
 */
public interface PostService extends BaseService<Post> {

    /**
     * Provide functionality to retrieve question by id.
     *
     * @param id of question
     * @return question
     */
    Post getQuestionById(Long id);

    /**
     * Provide functionality to retrieve all question.
     *
     * @return list of question
     */
    List<Post> getQuestions();

    /**
     * Provide functionality to crate a new question.
     *
     * @param body type Post
     */
    void createQuestion(Post body);

    /**
     * Provide functionality to update question.
     *
     * @param body Post
     */
    void updateQuestion(Post body);

    /**
     * Provide functionality to delete question by id.
     *
     * @param id of question
     */
    void deleteQuestionById(Long id);

    /**
     * Provide functionality to change question status.
     *
     * @param id     QuestionId
     * @param status question status
     */
    void changeStatus(Long id, Status status);

    /**
     * Provide functionality to retrieve answer by id.
     *
     * @param id of answer
     * @return Post object
     */
    Post getAnswerById(Long id);

    /**
     * Provide functionality to create a new answer.
     *
     * @param body Post
     */
    void createAnswer(Post body, Long questionId);

    /**
     * Provide functionality to update answer.
     *
     * @param body Post
     */
    void updateAnswer(Post body);

    /**
     * Provide functionality to delete answer by id.
     *
     * @param answerId of answer
     */
    void deleteAnswer(Long answerId);
}
