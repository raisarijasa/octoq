package com.mitrais.questionservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "answer")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userId;
    private String answer;
    private Date createdDate;
    private Date modifyDate;
    private String status;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
}
