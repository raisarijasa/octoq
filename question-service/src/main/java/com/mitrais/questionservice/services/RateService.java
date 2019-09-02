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
     * @param body Rate
     */
    void createRate(Rate body, Long postId);

    /**
     * delete rate by Id
     *
     * @param userId of rate
     */
    void deleteRate(String userId);
}
