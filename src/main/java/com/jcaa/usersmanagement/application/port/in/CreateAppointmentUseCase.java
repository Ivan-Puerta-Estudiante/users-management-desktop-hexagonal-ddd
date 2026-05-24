package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.CreateAppointmentCommand;
import com.jcaa.usersmanagement.domain.model.AppointmentModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface CreateAppointmentUseCase {
    AppointmentModel execute(@NotNull @Valid CreateAppointmentCommand command);
}
