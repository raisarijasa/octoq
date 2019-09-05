package com.mitrais.questionservice.dto;

import org.springframework.beans.factory.annotation.Value;

/**
 * Provide projection object.
 *
 * @author Rai Suardhyana Arijasa on 9/2/2019.
 */
public interface PostDto {
    String getTitle();

    String getDescription();

    @Value("#{target.title + ' ' + target.description}")
    String getTitleAndDescription();

}
