package com.learnKafka.user_service.controller;


import com.learnKafka.user_service.dto.UserRequestDTO;
import com.learnKafka.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final KafkaTemplate<String,String> kafkaTemplate;
    private final UserService userService;

    @PostMapping("/{message}")
    public ResponseEntity<String> sendMessage(@PathVariable String message) throws InterruptedException {

            kafkaTemplate.send("user-message",message);



        return ResponseEntity.ok("message queued");
    }

    @PostMapping()
    public ResponseEntity<UserRequestDTO> createUser(@RequestBody UserRequestDTO userDTO){
        log.info("-->{}",userDTO);
        return ResponseEntity.ok(userService.createUser(userDTO));
    }


}
