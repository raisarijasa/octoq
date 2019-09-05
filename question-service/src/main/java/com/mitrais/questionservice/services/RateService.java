package com.mitrais.questionservice.services;

import java.util.Optional;

import com.mitrais.questionservice.models.Rate;

/**
 * Provide functionality to manipulate Question and Answer input request.
 *
 * @author Rai Suardhyana Arijasa on 9/2/2019.
 */
public interface RateService {

    /**
     * Provide functionality to retrieve rate by id.
     *
     * @param id of rate
     * @return optional rate
     */
    Optional<Rate> findById(String id);

    /**
     * Provide functionality to save rate.
     *
     * @param data type rate
     */
    void save(Rate data);

    /**
     * Provide functionality to delete rate by id.
     *
     * @param id of rate
     */
    void deleteById(String id);

    /**
     * Provide functionality to create a new rate.
     *
     * @param body Rate
     */
    void createRate(Rate body, Long postId);

    /**
     * Provide functionality to delete question by id.
     *
     * @param userId of rate
     */
    void deleteRate(String userId);
}
