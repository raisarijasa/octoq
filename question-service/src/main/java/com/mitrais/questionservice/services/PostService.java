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
     * Get question by id
     *
     * @param id of question
     * @return question
     */
    Post getQuestionById(Long id);

    /**
     * get questions
     *
     * @return list of question
     */
    List<Post> getQuestions();

    /**
     * create new question
     *
     * @param body type Post
     */
    void createQuestion(Post body);

    /**
     * update question
     *
     * @param body Post
     */
    void updateQuestion(Post body);

    /**
     * Delete question by id
     *
     * @param id of question
     */
    void deleteQuestionById(Long id);

    /**
     * change question status
     *
     * @param id     QuestionId
     * @param status question status
     */
    void changeStatus(Long id, Status status);

    /**
     * get answer by id
     *
     * @param id of answer
     * @return Post object
     */
    Post getAnswerById(Long id);

    /**
     * create new answer
     *
     * @param body Post
     */
    void createAnswer(Post body, Long questionId);

    /**
     * update answer
     *
     * @param body Post
     */
    void updateAnswer(Post body);

    /**
     * delete answer by id
     *
     * @param answerId of answer
     */
    void deleteAnswer(Long answerId);
}
