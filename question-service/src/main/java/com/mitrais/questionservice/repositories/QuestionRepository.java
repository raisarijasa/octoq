package com.mitrais.questionservice.repositories;

import com.mitrais.questionservice.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
