package com.mitrais.questionservice.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Provide java object which map to Post table in database.
 *
 * @author Rai Suardhyana Arijasa on 9/2/2019.
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String userId;

    @Enumerated(EnumType.STRING)
    private Type type;
    private String title;
    @Column(nullable = false)
    private String description;
    private Date createdDate;
    private Date modifiedDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany()
    @JoinColumn(name = "question_id")
    private Set<Post> answers;

    @OneToMany()
    @JoinColumn(name = "post_id")
    private Set<Comment> comments;

    @OneToMany()
    @JoinColumn(name = "post_id")
    private Set<Rate> rates;
}
