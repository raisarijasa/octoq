package com.mitrais.questionservice.repositories;

import com.mitrais.questionservice.models.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
