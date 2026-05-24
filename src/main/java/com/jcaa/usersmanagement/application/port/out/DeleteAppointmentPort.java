package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.valueobject.AppointmentId;

public interface DeleteAppointmentPort {
    void delete(AppointmentId appointmentId);
}
