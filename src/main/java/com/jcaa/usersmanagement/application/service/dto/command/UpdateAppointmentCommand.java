package com.jcaa.usersmanagement.application.service.dto.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record UpdateAppointmentCommand(
        @NotBlank(message = "id must not be blank") String id,
        @NotBlank(message = "doctor id must not be blank") String doctorId,
        @NotNull(message = "appointment date must not be null") LocalDateTime appointmentDate,
        @NotBlank(message = "appointment reason must not be blank")
        @Size(min= 3, message = "appointment reason must have at least 3 characters") String appointmentReason
) {
}
