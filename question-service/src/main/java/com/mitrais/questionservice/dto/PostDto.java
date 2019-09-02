package com.mitrais.questionservice.dto;

import org.springframework.beans.factory.annotation.Value;

public interface PostDto {
    String getTitle();

    String getDescription();

    @Value("#{target.title + ' ' + target.description}")
    String getTitleAndDescription();

}
