package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.domain.exception.AppointmentAlreadyExistsException;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.AppointmentResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.AppointmentController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.AppointmentResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateAppointmentRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class CreateAppointmentHandler implements OperationHandler{
    private final AppointmentController appointmentController;
    private final ConsoleIO console;
    private final AppointmentResponsePrinter printer;

    @Override
    public void handle(){
        final String id =
                console.readRequired("Appointment ID : ");
        final String patientId =
                console.readRequired("Patient ID : ");
        final String doctorId =
                console.readRequired("Doctor ID : ");
        final String appointmentDate =
                console.readRequired("Date (yyyy-MM-ddTHH:mm:ss): ");
        final String appointmentReason =
                console.readRequired("Reason :");

        try {

            final AppointmentResponse created =
                    appointmentController.createAppointment(
                            new CreateAppointmentRequest(
                                    id,
                                    patientId,
                                    doctorId,
                                    appointmentDate,
                                    appointmentReason
                            )
                    );

            console.println("\n appointment created successfully");
            printer.print(created);
        } catch (final AppointmentAlreadyExistsException exception) {

            console.println(" Error: "+ exception.getMessage());
        }
    }
}
