package com.example.minisaraha.security;

import com.example.minisaraha.model.User;
import com.example.minisaraha.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DBUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public DBUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found!"));
        return new DBUserDetails(user);
    }
}
