package org.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String username;
    private String email;
    private Set<String> roles;
    private boolean enabled;
    private LocalDateTime createdAt;
}
