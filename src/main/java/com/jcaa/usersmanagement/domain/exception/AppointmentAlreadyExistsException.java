package com.jcaa.usersmanagement.domain.exception;

public class AppointmentAlreadyExistsException extends DomainException {

    private static final String MESSAGE_ID_EXISTS = "An appointment with id %s already exists.";

    private AppointmentAlreadyExistsException(final String message) {
        super(message);
    }

    public static AppointmentAlreadyExistsException becauseIdAlreadyExists(final String id) {
        return new AppointmentAlreadyExistsException(String.format(MESSAGE_ID_EXISTS, id));
    }

}
