package com.mitrais.questionservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userId;
    private String title;
    private String question;
    private Date createdDate;
    private Date modifyDate;
    private String status;

    @OneToMany(mappedBy = "tbl_question", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Question> answers;
}
