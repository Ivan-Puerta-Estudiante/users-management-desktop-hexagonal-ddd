package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.DeleteAppointmentUseCase;
import com.jcaa.usersmanagement.application.port.out.DeleteAppointmentPort;
import com.jcaa.usersmanagement.application.port.out.GetAppointmentByIdPort;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteAppointmentCommand;
import com.jcaa.usersmanagement.application.service.mapper.AppointmentApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.AppointmentNotFoundException;
import com.jcaa.usersmanagement.domain.valueobject.AppointmentId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import java.util.Set;

@RequiredArgsConstructor

public final class DeleteAppointmentService implements DeleteAppointmentUseCase {

    private final DeleteAppointmentPort deleteAppointmentPort;
    private final GetAppointmentByIdPort getAppointmentByIdPort;
    private final Validator validator;

    @Override
    public void execute(final DeleteAppointmentCommand command) {
        validateCommand(command);

        final AppointmentId appointmentId = AppointmentApplicationMapper.fromDeleteCommandToAppointmentId(command);
        ensureAppointmentExist(appointmentId);
        deleteAppointmentPort.delete(appointmentId);
    }
    private void validateCommand(final DeleteAppointmentCommand command) {
        final Set<ConstraintViolation<DeleteAppointmentCommand>> violations = validator.validate(command);
        if (!violations.isEmpty()){
            throw new ConstraintViolationException(violations);
        }
    }
    private void ensureAppointmentExist(final AppointmentId appointmentId) {
        getAppointmentByIdPort
                .getById(appointmentId)
                .orElseThrow(() -> AppointmentNotFoundException.becauseIdWasNotFound(appointmentId.value()));
    }
}
