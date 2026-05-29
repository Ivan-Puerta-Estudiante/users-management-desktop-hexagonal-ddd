package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler.*;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.AppointmentResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.menu.AppointmentMenuOption;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.AppointmentController;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class AppointmentManagementCli {

    private static final String BANNER =
            """
            ==========================================
                 Appointments Management System
            ==========================================""";

    private static final String MENU_BORDER = "  ==========================================";

    private final AppointmentController appointmentController;
    private final ConsoleIO console;

    public void start(){
        console.println(BANNER);
        final AppointmentResponsePrinter printer = new AppointmentResponsePrinter(console);
        runLoop(buildHandlers(printer));
    }

    private void runLoop(final Map<AppointmentMenuOption, OperationHandler> handlers) {
        boolean running = true;
        while (running) {
            printMenu();
            final int choice = console.readInt("\n Option: ");
            final Optional<AppointmentMenuOption> option = AppointmentMenuOption.fromNumber(choice);

            if (option.isEmpty()){
                console.println("Invalid option. Please try again.");

            }else if (option.get() == AppointmentMenuOption.EXIT){
                console.println("\n Goodbye!\n");
                running = false;
            }else {
                executeHandler(handlers, option.get());
            }
        }
    }

    private void executeHandler(final Map<AppointmentMenuOption, OperationHandler> handlers, final AppointmentMenuOption option) {
        try {
            handlers.get(option).handle();
        } catch (final ConstraintViolationException exception) {
            console.println(" Validation errors:");
            exception.getConstraintViolations()
                    .forEach(violation -> console.println("    - " + violation.getMessage()));
        } catch (final RuntimeException exception) {
            console.println(" Unexpected error: " + exception.getMessage());
        }
    }

    private Map<AppointmentMenuOption, OperationHandler> buildHandlers(final AppointmentResponsePrinter printer) {
        return Map.of(
                AppointmentMenuOption.LIST_APPOINTMENTS, new ListAppointmentHandler(appointmentController, printer),
                AppointmentMenuOption.FIND_APPOINTMENT, new FindAppointmentByIdHandler(appointmentController, console, printer),
                AppointmentMenuOption.CREATE_APPOINTMENT, new CreateAppointmentHandler(appointmentController, console, printer),
                AppointmentMenuOption.UPDATE_APPOINTMENT, new UpdateAppointmentHandler(appointmentController, console, printer),
                AppointmentMenuOption.DELETE_APPOINTMENT, new DeleteAppointmentHandler(appointmentController,console)
        );
    }

    private void printMenu() {
        console.println();
        console.println(MENU_BORDER);
        console.println("    Main Menu");
        console.println(MENU_BORDER);
        for (final AppointmentMenuOption option : AppointmentMenuOption.values()) {
            console.printf("    [%d] %s%n", option.getNumber(), option.getDescription());
        }
        console.println(MENU_BORDER);
    }


}
