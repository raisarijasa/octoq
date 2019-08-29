package com.mitrais.questionservice.services;

import com.mitrais.questionservice.dto.RateDto;
import com.mitrais.questionservice.exceptions.model.DataNotFoundException;
import com.mitrais.questionservice.models.Rate;
import com.mitrais.questionservice.repositories.PostRepository;
import com.mitrais.questionservice.repositories.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

/**
 * Rate Service
 */
@Service
public class RateServiceImpl implements RateService {
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
     */
    @Override
    public void createRate(RateDto body, Long postId) {
        postRepo.findById(postId)
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
                }).orElseThrow(() -> new DataNotFoundException("Data not found"));
    }

    /**
     * delete rate
     *
     * @param userId of rate
     */
    @Override
    public void deleteRate(String userId) {
        Optional<Rate> optRate = findById(userId);
        if (optRate.isPresent()) {
            deleteById(userId);
        } else {
            throw new DataNotFoundException("Rate Not Found");
        }
    }
}
