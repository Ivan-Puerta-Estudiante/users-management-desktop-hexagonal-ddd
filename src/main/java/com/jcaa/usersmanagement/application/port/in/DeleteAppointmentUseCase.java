package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.DeleteAppointmentCommand;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface DeleteAppointmentUseCase {
    void execute(@NotNull @Valid DeleteAppointmentCommand command);
}
