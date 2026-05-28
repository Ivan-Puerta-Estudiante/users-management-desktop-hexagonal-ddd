package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetAllAppointmentsUseCase;
import com.jcaa.usersmanagement.application.port.out.GetAllAppointmentsPort;
import com.jcaa.usersmanagement.domain.model.AppointmentModel;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class GetAllAppointmentsService implements GetAllAppointmentsUseCase {

    private final GetAllAppointmentsPort getAllAppointmentsPort;

    @Override
    public List<AppointmentModel> execute(){return getAllAppointmentsPort.getAll();}
}
