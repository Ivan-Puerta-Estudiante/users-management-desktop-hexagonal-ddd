package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.query.GetAppointmentByIdQuery;
import com.jcaa.usersmanagement.domain.model.AppointmentModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface GetAppointmentByIdUseCase {
    AppointmentModel execute(@NotNull@Valid GetAppointmentByIdQuery query);
}
