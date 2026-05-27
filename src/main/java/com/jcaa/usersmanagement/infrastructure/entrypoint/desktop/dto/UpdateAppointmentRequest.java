package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto;

public record UpdateAppointmentRequest(
        String id,
        String doctorId,
        String appointmentDate,
        String appointmentReason,
        String appointmentStatus
) {
}
