package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.CancelAppointmentCommand;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface CancelAppointmentUseCase {
    void execute(@NotNull @Valid CancelAppointmentCommand command);
}
