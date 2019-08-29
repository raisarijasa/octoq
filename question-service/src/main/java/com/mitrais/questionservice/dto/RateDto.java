package com.mitrais.questionservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class RateDto {
    private String userId;
    private int rating;
    private Date createdDate;
    private Date modifiedDate;
}
