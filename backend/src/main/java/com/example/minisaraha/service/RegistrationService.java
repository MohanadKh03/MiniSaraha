package com.example.minisaraha.service;

import com.example.minisaraha.exception.UsernameAlreadyTakenException;
import com.example.minisaraha.model.User;
import com.example.minisaraha.model.dto.UserDTO;
import com.example.minisaraha.repository.UserRepository;
import com.example.minisaraha.security.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    public void register(UserDTO user){
        if(userRepository.existsById(user.getUsername()))
            throw new UsernameAlreadyTakenException("Username (" + user.getUsername() + ") already exists, Choose another username!");
        User newUser = new User(user.getUsername(),
                passwordEncoder.encode(user.getPassword()),
                "ROLE_USER");
        userRepository.save(newUser);
    }
    public String login(UserDTO user){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword())
        );
        return jwtUtil.generateJwtToken(user.getUsername());
    }

}
