package com.mitrais.questionservice.models;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String description;
    private Date createdDate;
    private Date modifiedDate;
    @Enumerated(EnumType.STRING)
    private Status status;
}
