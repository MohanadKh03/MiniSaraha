package com.example.minisaraha.controller;

import com.example.minisaraha.model.dto.UserDTO;
import com.example.minisaraha.service.RegistrationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authentication")
@AllArgsConstructor
@Validated
public class AuthenticationController {
    private final RegistrationService registrationService;
    @PostMapping("/register")
    public ResponseEntity<Object> register(@Valid @RequestBody UserDTO user){
        registrationService.register(user);
        return new ResponseEntity<>("Registered Successfully",HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody UserDTO user){
        String token = registrationService.login(user);
        return new ResponseEntity<>(token,HttpStatus.OK);
    }
}
