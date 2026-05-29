package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MainCli {

    private final UserManagementCli userCli;
    private final AppointmentManagementCli appointmentCli;
    private final ConsoleIO console;

    public void start(){
        while (true) {
            console.println("""
                1. Users
                2. Appointments
                0. Exit
            """);

            int option = console.readInt("Option: ");

            switch (option) {
                case 1 -> userCli.start();
                case 2 -> appointmentCli.start();
                case 0 -> {
                    console.println("Bye!");
                    return;
                }
                default -> console.println("Invalid option");
            }
        }
    }

}
