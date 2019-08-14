package com.mitrais.questionservice.services;

import com.mitrais.questionservice.models.Question;
import com.mitrais.questionservice.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImp implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public void save(Question data) {
        questionRepository.save(data);
    }

    @Override
    public Question getDataById(Long id) {
        return questionRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        questionRepository.deleteById(id);
    }
}
