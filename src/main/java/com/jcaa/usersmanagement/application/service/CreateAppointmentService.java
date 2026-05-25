package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.CreateAppointmentUseCase;
import com.jcaa.usersmanagement.application.port.out.SaveAppointmentPort;
import com.jcaa.usersmanagement.application.service.dto.command.CreateAppointmentCommand;
import com.jcaa.usersmanagement.application.service.mapper.AppointmentApplicationMapper;
import com.jcaa.usersmanagement.domain.model.AppointmentModel;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

import java.util.Set;
@RequiredArgsConstructor

public final class CreateAppointmentService implements CreateAppointmentUseCase {

    private final SaveAppointmentPort saveAppointmentPort;
    private final Validator validator;

    @Override
    public AppointmentModel execute(final CreateAppointmentCommand command) {
        validateCommand(command);

        final AppointmentModel appointmentToSave = AppointmentApplicationMapper.fromCreateCommandToModel(command);
        final AppointmentModel savedAppointment = saveAppointmentPort.save(appointmentToSave);

        return savedAppointment;
    }

    private void validateCommand(final CreateAppointmentCommand command) {

        final Set<ConstraintViolation<CreateAppointmentCommand>> violations = validator.validate(command);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
