package com.mitrais.questionservice.dto;

import com.mitrais.questionservice.models.Comment;
import com.mitrais.questionservice.models.Post;
import com.mitrais.questionservice.models.Rate;
import com.mitrais.questionservice.models.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
public class AnswerDto {
    private Long id;
    private String userId;
    private String title;
    private String description;
    private Date createdDate;
    private Date modifiedDate;
    private Status status;
    private Set<Comment> comments;
    private Set<Rate> rates;
}
