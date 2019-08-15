package com.mitrais.questionservice.services;

import com.mitrais.questionservice.models.Question;
import com.mitrais.questionservice.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.domain.Sort.Direction.ASC;

@Service
public class QuestionServiceImp implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public void save(Question data) {
        questionRepository.save(data);
    }

    @Override
    public Question findDataById(Long id) {
        return questionRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        questionRepository.deleteById(id);
    }

    @Override
    public List<Question> findAll() {
        return questionRepository.findAll(new Sort(ASC, "createdDate"));
    }
}
