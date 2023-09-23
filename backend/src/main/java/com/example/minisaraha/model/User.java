package com.example.minisaraha.model;

import lombok.*;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.List;

@Table(value = "users")
@Getter
@Setter
public class User {
    @PrimaryKey
    private String username;
    private String password;
    private String role;
    private List<String> messages;
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
