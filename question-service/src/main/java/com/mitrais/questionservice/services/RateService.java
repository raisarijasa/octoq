package com.mitrais.questionservice.services;

import com.mitrais.questionservice.dto.RateDto;
import com.mitrais.questionservice.models.Rate;

import java.util.Optional;

/**
 * Rate Service
 */
public interface RateService {

    /**
     * find by id
     *
     * @param id of rate
     * @return optional rate
     */
    Optional<Rate> findById(String id);

    /**
     * save data
     *
     * @param data type rate
     */
    void save(Rate data);

    /**
     * delete by ud
     *
     * @param id of rate
     */
    void deleteById(String id);

    /**
     * create rate
     *
     * @param body RateDto
     */
    void createRate(RateDto body, Long postId);

    /**
     * delete rate by Id
     *
     * @param userId of rate
     */
    void deleteRate(String userId);
}
