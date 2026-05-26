package com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto;

public record AppointmentPersistenceDto(
        String id,
        String patientId,
        String doctorId,
        String appointmentDate,
        String appointmentReason,
        String appointmentStatus,
        String createdAt,
        String updatedAt
) {
}
