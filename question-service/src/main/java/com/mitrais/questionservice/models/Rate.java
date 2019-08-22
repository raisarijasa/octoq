package com.mitrais.questionservice.models;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "rates")
public class Rate {
    @Id
    private String userId;
    private int rating;
    private Date createdDate;
    private Date modifiedDate;
}
