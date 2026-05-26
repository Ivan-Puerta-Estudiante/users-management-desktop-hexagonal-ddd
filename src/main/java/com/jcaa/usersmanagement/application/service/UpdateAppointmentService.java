package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.UpdateAppointmentUseCase;
import com.jcaa.usersmanagement.application.port.out.GetAppointmentByIdPort;
import com.jcaa.usersmanagement.application.port.out.UpdateAppointmentPort;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateAppointmentCommand;
import com.jcaa.usersmanagement.application.service.mapper.AppointmentApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.AppointmentNotFoundException;
import com.jcaa.usersmanagement.domain.model.AppointmentModel;
import com.jcaa.usersmanagement.domain.valueobject.AppointmentId;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import jakarta.validation.ConstraintViolation;
import java.util.Set;

@RequiredArgsConstructor

public final class UpdateAppointmentService implements UpdateAppointmentUseCase {

    private final UpdateAppointmentPort updateAppointmentPort;
    private final GetAppointmentByIdPort getAppointmentByIdPort;
    private final Validator validator;

    @Override
    public AppointmentModel execute(final UpdateAppointmentCommand command) {
        validateCommand(command);

        final AppointmentId appointmentId = new AppointmentId(command.id());
        final AppointmentModel currentAppointment = findExistingAppointmentOrFail(appointmentId);
        final AppointmentModel appointmentToUpdate = AppointmentApplicationMapper.fromUpdateCommandToModel(
                command,
                currentAppointment.getPatientId(),
                currentAppointment.getAppointmentStatus()
        );
        return updateAppointmentPort.update(appointmentToUpdate);
    }

    private void validateCommand(final UpdateAppointmentCommand command) {
        final Set<ConstraintViolation<UpdateAppointmentCommand>> violations = validator.validate(command);
        if (!violations.isEmpty()){
            throw new ConstraintViolationException(violations);
        }
    }

    private AppointmentModel findExistingAppointmentOrFail(final AppointmentId appointmentId) {

        return getAppointmentByIdPort
                .getById(appointmentId)
                .orElseThrow(() -> AppointmentNotFoundException.becauseIdWasNotFound(appointmentId.value()));
    }
}
