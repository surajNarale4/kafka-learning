package com.learnKafka.user_service.service;


import com.learnKafka.event.UserEvent;
import com.learnKafka.user_service.dto.UserRequestDTO;
import com.learnKafka.user_service.entity.User;

import com.learnKafka.user_service.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final KafkaTemplate<Long , UserEvent> userEventKafkaTemplate;


    public UserRequestDTO createUser(UserRequestDTO userDTO) {
        User user =new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        UserEvent userEvent =new UserEvent();
        userEvent.setEmail(user.getEmail());
        userRepository.save(user);
        userEvent.setId(user.getId());
        userEventKafkaTemplate.send("create-user",user.getId(),userEvent);
        return userDTO;
    }
}
