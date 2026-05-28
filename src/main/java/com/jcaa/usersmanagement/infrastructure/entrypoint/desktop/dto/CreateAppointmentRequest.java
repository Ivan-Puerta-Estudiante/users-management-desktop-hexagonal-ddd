package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto;

public record CreateAppointmentRequest(
        String id,
        String patientId,
        String doctorId,
        String appointmentDate,
        String appointmentReason
) {
}
