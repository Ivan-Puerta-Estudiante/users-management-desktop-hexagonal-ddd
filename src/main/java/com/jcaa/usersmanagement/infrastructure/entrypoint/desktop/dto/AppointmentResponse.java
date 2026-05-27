package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto;

public record AppointmentResponse(
        String id,
        String patientId,
        String doctorId,
        String appointmentDate,
        String appointmentReason,
        String appointmentStatus
) {
}
