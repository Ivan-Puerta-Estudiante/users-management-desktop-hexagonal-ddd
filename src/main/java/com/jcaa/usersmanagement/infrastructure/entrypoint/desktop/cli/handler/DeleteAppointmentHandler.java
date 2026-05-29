package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.domain.exception.AppointmentNotFoundException;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.AppointmentController;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteAppointmentHandler implements OperationHandler{

    private final AppointmentController appointmentController;
    private final ConsoleIO console;

    @Override
    public void handle(){
        final String id = console.readRequired("Appointment ID to delete: ");
        try {
            appointmentController.deleteAppointment(id);
            console.println(" Appointment deleted successfully.");
        } catch (final AppointmentNotFoundException exception) {
            console.println(" Not found: " + exception.getMessage());
        }
    }
}
