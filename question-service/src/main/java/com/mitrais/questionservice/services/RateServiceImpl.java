package com.mitrais.questionservice.services;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitrais.questionservice.exceptions.model.DataNotFoundException;
import com.mitrais.questionservice.models.Rate;
import com.mitrais.questionservice.repositories.PostRepository;
import com.mitrais.questionservice.repositories.RateRepository;

/**
 * Provide implementation of functionality to manipulate Rate input request.
 *
 * @author Rai Suardhyana Arijasa on 9/2/2019.
 */
@Service
public class RateServiceImpl implements RateService {
    private RateRepository rateRepo;
    private PostRepository postRepo;

    @Autowired
    public RateServiceImpl(RateRepository rateRepo, PostRepository postRepo) {
        this.rateRepo = rateRepo;
        this.postRepo = postRepo;
    }

    @Override
    public void save(Rate data) {
        rateRepo.save(data);
    }

    public Optional<Rate> findById(String id) {
        return rateRepo.findById(id);
    }

    @Override
    public void deleteById(String id) {
        rateRepo.deleteById(id);
    }

    @Override
    public void createRate(Rate body, Long postId) {
        postRepo.findById(postId)
                .map(question -> {
                    Set<Rate> rates = question.getRates();
                    body
                            .setCreatedDate(new Date())
                            .setModifiedDate(null);
                    rates.add(body);
                    save(body);
                    return question;
                }).orElseThrow(() -> new DataNotFoundException("Data not found"));
    }

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
