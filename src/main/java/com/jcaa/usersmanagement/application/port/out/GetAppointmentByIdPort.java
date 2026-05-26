package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.AppointmentModel;
import com.jcaa.usersmanagement.domain.valueobject.AppointmentId;

import java.util.Optional;

public interface GetAppointmentByIdPort {
    Optional<AppointmentModel> getById(AppointmentId appointmentId);
}
