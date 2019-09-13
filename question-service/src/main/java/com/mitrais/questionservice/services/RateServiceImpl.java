package com.mitrais.questionservice.services;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.mitrais.questionservice.exceptions.model.DataNotFoundException;
import com.mitrais.questionservice.models.Rate;
import com.mitrais.questionservice.repositories.MessageRepository;
import com.mitrais.questionservice.repositories.PostRepository;
import com.mitrais.questionservice.repositories.RateRepository;

/**
 * Provide implementation of functionality to manipulate Rate input request.
 *
 * @author Rai Suardhyana Arijasa on 9/2/2019.
 */
@Service
public class RateServiceImpl implements RateService {
    private final RateRepository rateRepo;
    private final PostRepository postRepo;
    private final MessageRepository messageRepository;

    @Autowired
    public RateServiceImpl(RateRepository rateRepo, PostRepository postRepo, MessageRepository messageRepository) {
        this.rateRepo = rateRepo;
        this.postRepo = postRepo;
        this.messageRepository = messageRepository;
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
                }).orElseThrow(() -> new DataNotFoundException(messageRepository.DATA_NOT_FOUND));
    }

    @Override
    public void deleteRate(String userId) {
        try {
            deleteById(userId);
        } catch (EmptyResultDataAccessException e) {
            throw new DataNotFoundException(messageRepository.RATE_NOT_FOUND);
        }
    }
}
