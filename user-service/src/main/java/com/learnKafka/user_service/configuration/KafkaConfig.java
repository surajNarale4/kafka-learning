package com.learnKafka.user_service.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {


   @Bean
    public NewTopic createNewUserTopic(){
        return new NewTopic("create-user",3,(short)1);
    }

}
