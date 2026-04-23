package com.project.connect.posts_service.Configuration;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfig {
    @Bean
    public Encoder feignFormEncoder() {
        return new SpringFormEncoder();
    }
    @Bean
    public ModelMapper modelMapper()
    {
        return new ModelMapper();
    }
}
