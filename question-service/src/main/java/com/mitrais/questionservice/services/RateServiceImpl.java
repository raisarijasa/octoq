package com.mitrais.questionservice.services;

import com.mitrais.questionservice.dto.RateDto;
import com.mitrais.questionservice.exceptions.model.DataNotFoundException;
import com.mitrais.questionservice.models.Rate;
import com.mitrais.questionservice.repositories.PostRepository;
import com.mitrais.questionservice.repositories.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.springframework.http.ResponseEntity.ok;

/**
 * Rate Service
 */
@Service
public class RateServiceImpl extends BaseServiceImpl<Rate> implements RateService {
    private RateRepository rateRepo;
    private PostRepository postRepo;

    /**
     * Rate service controller
     *
     * @param rateRepo rate repository
     * @param postRepo post repository
     */
    @Autowired
    public RateServiceImpl(RateRepository rateRepo, PostRepository postRepo) {
        this.rateRepo = rateRepo;
        this.postRepo = postRepo;
    }

    /**
     * save rate data
     *
     * @param data type rate
     */
    @Override
    public void save(Rate data) {
        rateRepo.save(data);
    }

    /**
     * find rate by Id
     *
     * @param id of rate
     * @return optional rate
     */
    public Optional<Rate> findById(String id) {
        return rateRepo.findById(id);
    }

    /**
     * delete rate by id
     *
     * @param id of rate
     */
    @Override
    public void deleteById(String id) {
        rateRepo.deleteById(id);
    }

    /**
     * create rate
     *
     * @param body RateDto
     * @return response entity object
     */
    @Override
    public ResponseEntity createRate(RateDto body) {
        postRepo.findById(body.getPostId())
                .map(question -> {
                    Set<Rate> rates = question.getRates();
                    Rate rate = new Rate()
                            .setUserId(body.getUserId())
                            .setRating(body.getRating())
                            .setCreatedDate(new Date())
                            .setModifiedDate(null);
                    rates.add(rate);
                    save(rate);
                    return question;
                }).orElseThrow(() -> new DataNotFoundException("Question not found"));

        return ok(getResponse(
                false,
                "00001",
                "A new rate has been created successfully",
                new ArrayList<>()
        ));
    }

    /**
     * delete rate
     *
     * @param rateId of rate
     * @return response entity object
     */
    @Override
    public ResponseEntity deleteRate(String rateId) {
        Optional<Rate> optRate = findById(rateId);
        if (optRate.isPresent()) {
            deleteById(rateId);
            return ok(getResponse(
                    false,
                    "00004",
                    "Rate has been deleted successfully",
                    new ArrayList<>()
            ));
        } else {
            throw new DataNotFoundException("Rate Not Found");
        }
    }
}
