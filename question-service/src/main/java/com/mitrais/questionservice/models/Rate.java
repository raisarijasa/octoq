package com.mitrais.questionservice.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Provide java object which map to Rate table in database.
 *
 * @author Rai Suardhyana Arijasa on 9/2/2019.
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "rates")
public class Rate {
    @Id
    private String userId;
    @Column(nullable = false)
    private int rating;
    private Date createdDate;
    private Date modifiedDate;
}
