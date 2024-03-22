package com.danielks.curriculumapp.entities.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

public record CurriculumObjectDTO  (
        UUID id,
        @NotEmpty(message = "{origin.notempty}") String objectOrigin,
        @NotEmpty(message = "{header.notempty}") String header,
        @NotEmpty(message = "{body.notempty}") String body,
        @NotEmpty(message = "{type.notempty}") String type,
        Date beginDate,
        Date endDate,
        boolean ended,
        @NotNull Date creation
){
}
