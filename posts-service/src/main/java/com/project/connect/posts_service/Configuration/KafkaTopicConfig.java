package com.project.connect.posts_service.Configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic postCreate()
    {
        return  new NewTopic("post-create-topic",3,(short) 1);
    }

    @Bean
    public  NewTopic postLike()
    {
        return  new NewTopic("post-like-topic",3,(short) 1);
    }
}
