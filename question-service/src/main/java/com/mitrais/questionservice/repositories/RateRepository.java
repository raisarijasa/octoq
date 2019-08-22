package com.mitrais.questionservice.repositories;

import com.mitrais.questionservice.models.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Rate Repository
 */
public interface RateRepository extends JpaRepository<Rate, String> {
}
