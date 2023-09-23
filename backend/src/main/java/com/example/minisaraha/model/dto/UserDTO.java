package com.example.minisaraha.model.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    @Size(min = 8, message = "Username must be at least 8 characters long!")
    private String username;
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must be strong (at least one uppercase, one lowercase, one digit, and one special character, minimum 8 characters)"
    )
    private String password;
}
