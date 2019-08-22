package com.mitrais.questionservice.models;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;

    @Enumerated(EnumType.STRING)
    private Type type;
    private String title;
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
