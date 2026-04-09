package org.example.backend.dto;

import lombok.Data;

import java.util.Set;

@Data
public class UpdateUserRequest {

    private Set<String> roles;
    private boolean enabled;
}
