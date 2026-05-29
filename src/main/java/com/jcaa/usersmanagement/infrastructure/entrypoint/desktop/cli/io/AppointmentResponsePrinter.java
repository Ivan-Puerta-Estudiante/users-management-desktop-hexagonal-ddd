package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.AppointmentResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class AppointmentResponsePrinter {

    private static final String SEPARATOR = "-".repeat(52);
    private static final String ROW_FORMAT = " %-20s : %s%n";

    private final ConsoleIO console;

    public void print(final AppointmentResponse response) {
        console.println(SEPARATOR);

        console.printf(ROW_FORMAT, "ID", response.id());
        console.printf(ROW_FORMAT, "Patient ID", response.patientId());
        console.printf(ROW_FORMAT, "Doctor ID", response.doctorId());
        console.printf(ROW_FORMAT, "Date", response.appointmentDate());
        console.printf(ROW_FORMAT, "Reason", response.appointmentReason());
        console.printf(ROW_FORMAT, "Status", response.appointmentStatus());

        console.println(SEPARATOR);
    }

    public void printList(final List<AppointmentResponse> appointments) {

        if(appointments.isEmpty()){
            console.println("No appointments found.");
            return;
        }
        console.printf(
                "%n Total: %d appointment(s)%n",
                appointments.size()
        );
        appointments.forEach(this::print);
    }

}
