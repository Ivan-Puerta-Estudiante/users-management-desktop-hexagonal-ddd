package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetAppointmentByIdUseCase;
import com.jcaa.usersmanagement.application.port.out.GetAppointmentByIdPort;
import com.jcaa.usersmanagement.application.service.dto.query.GetAppointmentByIdQuery;
import com.jcaa.usersmanagement.application.service.mapper.AppointmentApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.AppointmentNotFoundException;
import com.jcaa.usersmanagement.domain.model.AppointmentModel;
import com.jcaa.usersmanagement.domain.valueobject.AppointmentId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public final class GetAppointmentByIdService implements GetAppointmentByIdUseCase {

    private final GetAppointmentByIdPort getAppointmentByIdPort;
    private final Validator validator;

    @Override
    public AppointmentModel execute(final GetAppointmentByIdQuery query){
        validateQuery(query);

        final AppointmentId appointmentId = AppointmentApplicationMapper.fromGetAppointmentByIdQueryToAppointmentId(query);
        return getAppointmentByIdPort
                .getById(appointmentId)
                .orElseThrow(() -> AppointmentNotFoundException.becauseIdWasNotFound(appointmentId.value()));
    }

    private void validateQuery(final GetAppointmentByIdQuery query) {
        final Set<ConstraintViolation<GetAppointmentByIdQuery>> violations = validator.validate(query);
        if(!violations.isEmpty()){
            throw new ConstraintViolationException(violations);
        }
    }

}
