package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.domain.model.AppointmentModel;

import java.util.List;

public interface GetAllAppointmentsUseCase {
    List<AppointmentModel> execute();
}
