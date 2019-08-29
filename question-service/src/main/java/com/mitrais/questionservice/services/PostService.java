package com.mitrais.questionservice.services;

import com.mitrais.questionservice.dto.AnswerDto;
import com.mitrais.questionservice.dto.QuestionDto;
import com.mitrais.questionservice.models.Post;

import java.util.List;

/**
 * Post Service
 */
public interface PostService extends BaseService<Post> {

    /**
     * Get question by id
     *
     * @param id of question
     * @return question
     */
    QuestionDto getQuestionById(Long id);

    /**
     * get questions
     *
     * @return list of question
     */
    List<QuestionDto> getQuestions();

    /**
     * create new question
     *
     * @param body type QuestionDto
     */
    void createQuestion(QuestionDto body);

    /**
     * update question
     *
     * @param body QuestionDto
     */
    void updateQuestion(QuestionDto body);

    /**
     * Delete question by id
     *
     * @param id of question
     */
    void deleteQuestionById(Long id);

    /**
     * change question status
     *
     * @param body QuestionDto
     */
    void changeStatus(QuestionDto body);

    /**
     * get answer by id
     *
     * @param id of answer
     * @return answer dto object
     */
    AnswerDto getAnswerById(Long id);

    /**
     * create new answer
     *
     * @param body AnswerDto
     */
    void createAnswer(AnswerDto body, Long questionId);

    /**
     * update answer
     *
     * @param body AnswerDto
     */
    void updateAnswer(AnswerDto body);

    /**
     * delete answer by id
     *
     * @param questionId of question
     * @param answerId   of answer
     */
    void deleteAnswer(Long questionId, Long answerId);
}
