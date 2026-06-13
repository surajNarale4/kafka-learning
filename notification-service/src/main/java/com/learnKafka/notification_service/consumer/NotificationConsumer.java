package com.learnKafka.notification_service.consumer;



import com.learnKafka.event.UserEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationConsumer {

    @KafkaListener(topics = "user-message")
    public void listener(String message){

        log.info(message);
    }

    @KafkaListener(topics = "create-user")
    public void listener(UserEvent userEvent){

        log.info("user is created {}",userEvent);
    }
}
