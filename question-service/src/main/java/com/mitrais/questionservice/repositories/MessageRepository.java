package com.mitrais.questionservice.repositories;

import org.springframework.beans.factory.annotation.Value;

/**
 * Provide message repository.
 *
 * @author Rai Suardhyana Arijasa on 9/5/2019.
 */
public class MessageRepository {
    @Value("${question.create.success}")
    public String CREATE_QUESTION_SUCCESS;
    public String CREATE_QUESTION_SUCCESS_CODE = "001001";

    @Value("${question.update.success}")
    public String UPDATE_QUESTION_SUCCESS;
    public String UPDATE_QUESTION_SUCCESS_CODE = "001002";

    @Value("${question.retrieve.success}")
    public String RETRIEVE_QUESTION_SUCCESS;
    public String RETRIEVE_QUESTION_SUCCESS_CODE = "001003";

    @Value("${question.delete.success}")
    public String DELETE_QUESTION_SUCCESS;
    public String DELETE_QUESTION_SUCCESS_CODE = "001004";

    @Value("${question.id.mandatory}")
    public String QUESTION_ID_MANDATORY;
    public String QUESTION_ID_MANDATORY_CODE = "001005";

    @Value("${question.not.found}")
    public String QUESTION_NOT_FOUND;
    public String QUESTION_NOT_FOUND_CODE = "001006";

    @Value("${question.exist}")
    public String QUESTION_EXIST;
    public String QUESTION_EXIST_CODE = "001006";

    @Value("${answer.create.success}")
    public String CREATE_ANSWER_SUCCESS;
    public String CREATE_ANSWER_SUCCESS_CODE = "002001";

    @Value("${answer.update.success}")
    public String UPDATE_ANSWER_SUCCESS;
    public String UPDATE_ANSWER_SUCCESS_CODE = "002002";

    @Value("${answer.retrieve.success}")
    public String RETRIEVE_ANSWER_SUCCESS;
    public String RETRIEVE_ANSWER_SUCCESS_CODE = "002003";

    @Value("${answer.delete.success}")
    public String DELETE_ANSWER_SUCCESS;
    public String DELETE_ANSWER_SUCCESS_CODE = "002004";

    @Value("${answer.create.success}")
    public String ANSWER_ID_MANDATORY;
    public String ANSWER_ID_MANDATORY_CODE = "002005";

    @Value("${answer.not.found}")
    public String ANSWER_NOT_FOUND;
    public String ANSWER_NOT_FOUND_CODE = "002006";

    @Value("${answer.exist}")
    public String ANSWER_EXIST;
    public String ANSWER_EXIST_CODE = "002006";

    @Value("${comment.create.success}")
    public String CREATE_COMMENT_SUCCESS;
    public String CREATE_COMMENT_SUCCESS_CODE = "003001";

    @Value("${comment.update.success}")
    public String UPDATE_COMMENT_SUCCESS;
    public String UPDATE_COMMENT_SUCCESS_CODE = "003002";

    @Value("${comment.retrieve.success}")
    public String RETRIEVE_COMMENT_SUCCESS;
    public String RETRIEVE_COMMENT_SUCCESS_CODE = "003003";

    @Value("${comment.delete.success}")
    public String DELETE_COMMENT_SUCCESS;
    public String DELETE_COMMENT_SUCCESS_CODE = "003004";

    @Value("${comment.id.mandatory}")
    public String COMMENT_ID_MANDATORY;
    public String COMMENT_ID_MANDATORY_CODE = "003005";

    @Value("${comment.not.found}")
    public String COMMENT_NOT_FOUND;
    public String COMMENT_NOT_FOUND_CODE = "004006";

    @Value("${comment.exist}")
    public String COMMENT_EXIST;
    public String COMMENT_EXIST_CODE = "005006";

    @Value("${rate.create.success}")
    public String CREATE_RATE_SUCCESS;
    public String CREATE_RATE_SUCCESS_CODE = "004001";

    @Value("${rate.update.success}")
    public String UPDATE_RATE_SUCCESS;
    public String UPDATE_RATE_SUCCESS_CODE = "004002";

    @Value("${rate.retrieve.success}")
    public String RETRIEVE_RATE_SUCCESS;
    public String RETRIEVE_RATE_SUCCESS_CODE = "004003";

    @Value("${rate.delete.success}")
    public String DELETE_RATE_SUCCESS;
    public String DELETE_RATE_SUCCESS_CODE = "004004";

    @Value("${rate.id.mandatory}")
    public String RATE_ID_MANDATORY;
    public String RATE_ID_MANDATORY_CODE = "004005";

    @Value("${rate.not.found}")
    public String RATE_NOT_FOUND;
    public String RATE_NOT_FOUND_CODE = "004006";

    @Value("${rate.exist}")
    public String RATE_EXIST;
    public String RATE_EXIST_CODE = "004006";

    @Value("${data.not.found}")
    public String DATA_NOT_FOUND;
    public String DATA_NOT_FOUND_CODE = "100001";
}
