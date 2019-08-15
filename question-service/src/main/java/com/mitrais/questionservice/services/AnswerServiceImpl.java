package com.mitrais.questionservice.services;

import com.mitrais.questionservice.models.Answer;
import com.mitrais.questionservice.repositories.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.domain.Sort.Direction.ASC;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public void save(Answer data) {
        answerRepository.save(data);
    }

    @Override
    public Answer findDataById(Long id) {
        return answerRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        answerRepository.deleteById(id);
    }

    @Override
    public List<Answer> findAll() {
        return answerRepository.findAll(new Sort(ASC, "createdDate"));
    }

//    @Override
//    public List<Answer> findByQuestionId(Long questionId) {
//        return answerRepository.findByQuestionId(questionId);
//    }
}
