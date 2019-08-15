package com.mitrais.questionservice.repositories;

import com.mitrais.questionservice.models.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
//    List<Answer> findByQuestionId(Long questionId);
}
