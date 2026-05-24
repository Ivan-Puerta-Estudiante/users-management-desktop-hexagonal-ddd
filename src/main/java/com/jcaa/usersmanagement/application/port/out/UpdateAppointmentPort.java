package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.AppointmentModel;

public interface UpdateAppointmentPort {
    AppointmentModel update(AppointmentModel appointmentModel);
}
