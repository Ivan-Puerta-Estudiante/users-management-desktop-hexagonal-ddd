package com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity;

public record AppointmentEntity(
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
