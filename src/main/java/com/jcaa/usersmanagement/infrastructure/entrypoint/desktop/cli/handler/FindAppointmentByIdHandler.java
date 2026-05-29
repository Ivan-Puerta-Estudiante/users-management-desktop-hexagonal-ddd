package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.domain.exception.AppointmentNotFoundException;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.AppointmentResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.AppointmentController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.AppointmentResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FindAppointmentByIdHandler implements OperationHandler{

    private final AppointmentController appointmentController;
    private final ConsoleIO console;
    private final AppointmentResponsePrinter printer;

    @Override
    public void handle(){
        final String id = console.readRequired("Appointment ID");
        try {
            final AppointmentResponse appointment = appointmentController.findAppointmentById(id);
            printer.print(appointment);
        } catch (final AppointmentNotFoundException exception) {
            console.println(" Not found: " + exception.getMessage());
        }
    }
}
