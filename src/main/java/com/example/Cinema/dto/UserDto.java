package com.example.Cinema.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record UserDto (String username,
        List<RoleDto> roles,
        String firstName,
        String lastName,
        String emailAddress) {}
