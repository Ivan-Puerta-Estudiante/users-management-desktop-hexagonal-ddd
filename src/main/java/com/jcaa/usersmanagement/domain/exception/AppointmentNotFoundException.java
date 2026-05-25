package com.jcaa.usersmanagement.domain.exception;

public final class AppointmentNotFoundException extends DomainException {

    private static final String MESSAGE_BY_ID = "The Appointment with id '%s' was not found.";

    private AppointmentNotFoundException(final String message) {
        super(message);
    }

    public static AppointmentNotFoundException becauseIdWasNotFound(final String appointmentId) {
        return new AppointmentNotFoundException(String.format(MESSAGE_BY_ID, appointmentId));
    }
}
