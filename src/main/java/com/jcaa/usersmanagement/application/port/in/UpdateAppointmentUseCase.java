package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.UpdateAppointmentCommand;
import com.jcaa.usersmanagement.domain.model.AppointmentModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface UpdateAppointmentUseCase {
    AppointmentModel execute(@NotNull @Valid UpdateAppointmentCommand command);
}
