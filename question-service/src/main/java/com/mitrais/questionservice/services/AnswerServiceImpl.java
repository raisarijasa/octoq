package com.mitrais.questionservice.services;

import com.mitrais.questionservice.models.Answer;
import com.mitrais.questionservice.repositories.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public void save(Answer data) {
        answerRepository.save(data);
    }

    @Override
    public Answer getDataById(Long id) {
        return answerRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        answerRepository.deleteById(id);
    }
}
