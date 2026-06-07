package com.learnKafka.user_service.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final KafkaTemplate<String,String> kafkaTemplate;

    @PostMapping("/{message}")
    public ResponseEntity<String> sendMessage(@PathVariable String message) throws InterruptedException {

        for(int i=0;i<=1000;i++){
            kafkaTemplate.send("user-message",message+i);
            Thread.sleep(1000);
        }

        return ResponseEntity.ok("message queued");
    }


}
