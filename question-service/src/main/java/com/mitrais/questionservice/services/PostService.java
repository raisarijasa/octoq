package com.mitrais.questionservice.services;

import com.mitrais.questionservice.dto.AnswerDto;
import com.mitrais.questionservice.dto.QuestionDto;
import com.mitrais.questionservice.models.Post;
import org.springframework.http.ResponseEntity;

/**
 * Post Service
 */
public interface PostService extends BaseService<Post> {

    /**
     * Get question by id
     *
     * @param id of question
     * @return response entity object
     */
    ResponseEntity getQuestionById(Long id);

    /**
     * get questions
     *
     * @return response entity
     */
    ResponseEntity getQuestions();

    /**
     * create new question
     *
     * @param body type QuestionDto
     * @return response entity object
     */
    ResponseEntity createQuestion(QuestionDto body);

    /**
     * update question
     *
     * @param body QuestionDto
     * @return response entity
     */
    ResponseEntity updateQuestion(QuestionDto body);

    /**
     * Delete question by id
     *
     * @param id of question
     * @return response entity
     */
    ResponseEntity deleteQuestionById(Long id);

    /**
     * change question status
     *
     * @param body QuestionDto
     * @return response entity object
     */
    ResponseEntity changeStatus(QuestionDto body);

    /**
     * get answer by id
     *
     * @param id of answer
     * @return response entity object
     */
    ResponseEntity getAnswerById(Long id);

    /**
     * create new answer
     *
     * @param body AnswerDto
     * @return response entity
     */
    ResponseEntity createAnswer(AnswerDto body);

    /**
     * update answer
     *
     * @param body AnswerDto
     * @return response entity
     */
    ResponseEntity updateAnswer(AnswerDto body);

    /**
     * delete answer by id
     *
     * @param questionId of question
     * @param answerId   of answer
     * @return response entity object
     */
    ResponseEntity deleteAnswer(Long questionId, Long answerId);
}
