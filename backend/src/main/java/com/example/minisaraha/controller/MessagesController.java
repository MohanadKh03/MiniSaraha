package com.example.minisaraha.controller;

import com.example.minisaraha.service.MessagesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/messages")
@AllArgsConstructor
public class MessagesController {
    private final MessagesService messagesService;
    @GetMapping("/{username}")
    @PreAuthorize("#username == authentication.principal.username")
    public ResponseEntity<Object> getMessages(@PathVariable String username) {
        return new ResponseEntity<>(messagesService.getMessages(username), HttpStatus.OK);
    }

    @PostMapping("/{username}")
    public ResponseEntity<Object> sendMessageTo(@PathVariable String username,
                                                @RequestBody Map.Entry<String,String> message)
    {
        messagesService.sendAnonymousMessageTo(username,message.getValue());
        return new ResponseEntity<>("Message successfully sent!", HttpStatus.OK);
    }

}
