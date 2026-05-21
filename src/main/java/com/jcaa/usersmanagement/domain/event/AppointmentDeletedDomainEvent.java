package com.jcaa.usersmanagement.domain.event;

import com.jcaa.usersmanagement.domain.valueobject.AppointmentId;

import java.util.Map;

public final class AppointmentDeletedDomainEvent extends DomainEvent{

    private static final String EVENT_NAME = "appointment.deleted";

    private final AppointmentId appointmentId;

    public AppointmentDeletedDomainEvent(final AppointmentId appointmentId) {
        super(EVENT_NAME);
        this.appointmentId = appointmentId;
    }

    @Override
    public Map<String, String> payload() {
        return Map.of(
                "id", appointmentId.value()
        );
    }
}
