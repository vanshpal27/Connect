package com.project.connect.connections_service.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaProjectConfig {


    @Bean
    public NewTopic sendConnectionRequest()
    {
        return  new NewTopic("send-connection-request-topic",3,(short)1);
    }

    @Bean
    public  NewTopic  acceptConnectionRequest()
    {
        return new NewTopic("accept-connection-request-topic",3,(short) 1);
    }

    @Bean
    public  NewTopic rejectConnectionRequest()
    {
        return  new NewTopic("reject-connection-request-topic",3,(short) 1);
    }
}
