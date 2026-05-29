package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.menu;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
@Getter
public enum AppointmentMenuOption {

    LIST_APPOINTMENTS(1, "List all appointments"),
    FIND_APPOINTMENT(2, "Find appointment by ID"),
    CREATE_APPOINTMENT(3, "Create appointment"),
    UPDATE_APPOINTMENT(4, "Update appointment"),
    DELETE_APPOINTMENT(5, "Delete appointment"),
    EXIT(0, "Exit");

    private final int number;
    private final String description;

    public static Optional<AppointmentMenuOption> fromNumber(final int number) {
        for (final AppointmentMenuOption option : values()) {
            if (option.number == number) {
                return Optional.of(option);
            }
        }
        return Optional.empty();
    }
}
