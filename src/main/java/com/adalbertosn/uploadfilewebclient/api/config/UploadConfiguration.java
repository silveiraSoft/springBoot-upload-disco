package com.adalbertosn.uploadfilewebclient.api.config;

import com.adalbertosn.uploadfilewebclient.core.ReactiveUpload;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
@Qualifier("UploadConfiguration")
public class UploadConfiguration {

    @Bean
    public WebClient webClientUpload(WebClient.Builder builder) {
        return builder.baseUrl("http://localhost:8081")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Bean
    public WebClient webClientLocalUpload(WebClient.Builder builder) {
        return builder.baseUrl("http://localhost:8081")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Bean
    public ReactiveUpload reactiveUpload(){
        ReactiveUpload ru = new ReactiveUpload();
        ru.atrib1 = "test bean upload";
        return ru;
        //return new ReactiveUpload();
    }
}
