package com.aacfahim.streaming_platform.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configurePathMatch(org.springframework.web.servlet.config.annotation.PathMatchConfigurer configurer) {
        configurer.addPathPrefix(ApiPath.API_BASE_PATH,
                org.springframework.web.method.HandlerTypePredicate.forAnnotation(org.springframework.web.bind.annotation.RestController.class));
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowedOrigins("*");
    }
}
