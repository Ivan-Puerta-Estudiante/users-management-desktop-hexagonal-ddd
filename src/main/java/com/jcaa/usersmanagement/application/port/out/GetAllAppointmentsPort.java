package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.AppointmentModel;

import java.util.List;

public interface GetAllAppointmentsPort {
    List<AppointmentModel> getAll();
}
