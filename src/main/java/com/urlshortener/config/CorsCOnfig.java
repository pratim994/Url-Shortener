package com.urlshortener.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class CorsConfig implemments WebMvcCongigurer {

    @Override
    public void addCorsMApping(CorsRegistry registry) {

        registry.addMapping("/**")
                .allowedOrgins("*")
                .allowedMethods("GET", "POST", "OPTIONS")
                .allowedHeaders("*");
    }
}