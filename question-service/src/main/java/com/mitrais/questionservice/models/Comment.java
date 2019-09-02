package com.mitrais.questionservice.models;

import javax.persistence.*;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Provide java object which map to Comment table in database..
 *
 * @author Rai Suardhyana Arijasa on 9/2/2019.
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String userId;
    @Column(nullable = false)
    private String description;
    private Date createdDate;
    private Date modifiedDate;
    @Enumerated(EnumType.STRING)
    private Status status;
}
