package com.mitrais.questionservice.dto;

import com.mitrais.questionservice.models.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class CommentDto {
    private Long id;
    private String userId;
    private String description;
    private Date createdDate;
    private Date modifiedDate;
    private Status status;
}
