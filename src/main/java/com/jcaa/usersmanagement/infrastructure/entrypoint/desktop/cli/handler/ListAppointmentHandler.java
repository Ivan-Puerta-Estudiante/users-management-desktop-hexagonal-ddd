package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.AppointmentResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.AppointmentController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.AppointmentResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class ListAppointmentHandler implements OperationHandler{

    private final AppointmentController appointmentController;
    private final AppointmentResponsePrinter printer;

    @Override
    public void handle() {
        final List<AppointmentResponse> appointments = appointmentController.listAllAppointments();
        printer.printList(appointments);
    }
}
