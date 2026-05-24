package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.AppointmentModel;

public interface SaveAppointmentPort {
    AppointmentModel save(AppointmentModel appointment);
}
