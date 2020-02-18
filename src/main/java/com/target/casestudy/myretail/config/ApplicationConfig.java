package com.target.casestudy.myretail.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableConfigurationProperties()
//@NoArgsConstructor
public class ApplicationConfig {

    @Value("${redsky.url}")
    private String REDSKY_URL;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public ObjectMapper objectMapper() {return new ObjectMapper();}

//    @Bean
//    public PriceDao priceDao() {
//        return new PriceDaoImpl();
//    }
//
    @Bean
    public String getRedskyUrl() {
        return REDSKY_URL;
    }
//
//    @Bean
//    public Cluster cluster() {
//        return cluster;
//    }

//    @Bean
//    public Session session() {
//        return session;
//    }

}