package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller;

import com.jcaa.usersmanagement.application.port.in.*;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.AppointmentResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateAppointmentRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UpdateAppointmentRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper.AppointmentDesktopMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class AppointmentController {

    private final CreateAppointmentUseCase createAppointmentUseCase;
    private final UpdateAppointmentUseCase updateAppointmentUseCase;
    private final DeleteAppointmentUseCase deleteAppointmentUseCase;
    private final GetAppointmentByIdUseCase getAppointmentByIdUseCase;
    private final GetAllAppointmentsUseCase getAllAppointmentsUseCase;

    public List<AppointmentResponse> listAllAppointments() {
        final var appointments = getAllAppointmentsUseCase.execute();
        return AppointmentDesktopMapper.toResponseList(appointments);
    }

    public AppointmentResponse findAppointmentById(final String id) {
        final var query = AppointmentDesktopMapper.toGetByIdQuery(id);
        final var appointment = getAppointmentByIdUseCase.execute(query);
        return AppointmentDesktopMapper.toResponse(appointment);
    }

    public AppointmentResponse createAppointment(final CreateAppointmentRequest request) {
        final var command = AppointmentDesktopMapper.toCreateCommand(request);
        final var appointment = createAppointmentUseCase.execute(command);
        return AppointmentDesktopMapper.toResponse(appointment);
    }

    public AppointmentResponse updateAppointment(final UpdateAppointmentRequest request) {
        final var command = AppointmentDesktopMapper.toUpdateCommand(request);
        final var appointment = updateAppointmentUseCase.execute(command);
        return AppointmentDesktopMapper.toResponse(appointment);
    }

    public void deleteAppointment(final String id) {
        final var command = AppointmentDesktopMapper.toDeleteCommand(id);
        deleteAppointmentUseCase.execute(command);
    }

}
