package com.example.minisaraha.service;

import com.example.minisaraha.model.User;
import com.example.minisaraha.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MessagesService {
    private final UserRepository userRepository;

    public List<String> getMessages(String username){
        return userRepository.findById(username).get().getMessages();
    }
    public void sendAnonymousMessageTo(String username,String message){
        User user = userRepository.findById(username).get();
        if(user.getMessages() == null)
            user.setMessages(new ArrayList<>());
        user.getMessages().add(message);
        userRepository.save(user);
    }

}
