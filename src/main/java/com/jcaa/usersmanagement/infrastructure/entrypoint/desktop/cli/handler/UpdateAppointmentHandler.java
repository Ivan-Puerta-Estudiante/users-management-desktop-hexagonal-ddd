package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.domain.exception.AppointmentNotFoundException;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.AppointmentResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.AppointmentController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.AppointmentResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UpdateAppointmentRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class UpdateAppointmentHandler implements OperationHandler{

    private final AppointmentController appointmentController;
    private final ConsoleIO console;
    private final AppointmentResponsePrinter printer;

    public void handle() {
        final String id = console.readRequired("Appointment ID : ");
        final String idDoctor = console.readRequired("Doctor ID : ");
        final String appointmentDate = console.readRequired("Appointment DATE : ");
        final String appointmentReason = console.readRequired("Appointment REASON : ");
        final String appointmentStatus = console.readRequired("Appointment STATUS : ");

        try{

            final AppointmentResponse updated = appointmentController.updateAppointment(
                    new UpdateAppointmentRequest(
                            id,
                            idDoctor,
                            appointmentDate,
                            appointmentReason,
                            appointmentStatus
                    )
            );
            console.println("\n Appointment Updated Successfully.");
            printer.print(updated);
        } catch(final AppointmentNotFoundException exception){
            console.println(" Not Found: " + exception.getMessage());
        }
    }

}
