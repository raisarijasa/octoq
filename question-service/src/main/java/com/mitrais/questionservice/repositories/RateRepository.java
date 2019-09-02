package com.mitrais.questionservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mitrais.questionservice.models.Rate;

/**
 * Provide functionality to manipulate Rate data in database.
 *
 * @author Rai Suardhyana Arijasa on 9/2/2019.
 */
public interface RateRepository extends JpaRepository<Rate, String> {
}
