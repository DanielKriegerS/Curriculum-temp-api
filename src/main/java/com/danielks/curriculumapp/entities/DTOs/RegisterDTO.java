package com.danielks.curriculumapp.entities.DTOs;

import com.danielks.curriculumapp.entities.UserRole;

public record RegisterDTO(
        String username,
        String password,
        UserRole role
) {
}
