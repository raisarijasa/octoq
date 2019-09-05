package com.mitrais.questionservice.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import com.mitrais.questionservice.repositories.MessageRepository;

/**
 * Provide swagger configuration.
 *
 * @author Rai Suardhyana Arijasa on 9/5/2019.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .paths(Predicates.not(PathSelectors.regex("/error/*")))
                .paths(Predicates.not(PathSelectors.regex("/actuator")))
                .build()
//                .securitySchemes(Arrays.asList(apiKey()))
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Oqtoc REST API")
                .description("Oqtoc Question REST API")
                .version("0.1")
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey("Authorization", "Authorization", "header");
    }

    @Bean
    public MessageRepository defaultMessageRepository() {
        return new MessageRepository();
    }
}
