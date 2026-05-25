package com.jcaa.usersmanagement.application.service.mapper;

import com.jcaa.usersmanagement.application.service.dto.command.CreateAppointmentCommand;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteAppointmentCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateAppointmentCommand;
import com.jcaa.usersmanagement.application.service.dto.query.GetAppointmentByIdQuery;
import com.jcaa.usersmanagement.domain.enums.AppointmentStatus;
import com.jcaa.usersmanagement.domain.model.AppointmentModel;
import com.jcaa.usersmanagement.domain.valueobject.*;
import lombok.experimental.UtilityClass;

@UtilityClass

public class AppointmentApplicationMapper {

    public AppointmentModel fromCreateCommandToModel(final CreateAppointmentCommand command) {
        return AppointmentModel.create(
                new AppointmentId(command.id()),
                new PatientId(command.patientId()),
                new DoctorId(command.doctorId()),
                new AppointmentDate(command.appointmentDate()),
                new AppointmentReason(command.appointmentReason())
        );
    }

    public AppointmentModel fromUpdateCommandToModel(
            final UpdateAppointmentCommand command,
            final PatientId currentPatientId,
            final AppointmentStatus currentStatus) {
        return new AppointmentModel(
                new AppointmentId(command.id()),
                currentPatientId,
                new DoctorId(command.doctorId()),
                new AppointmentDate(command.appointmentDate()),
                new AppointmentReason(command.appointmentReason()),
                currentStatus
        );
    }

    public AppointmentId fromGetAppointmentByIdQueryToAppointmentId(final GetAppointmentByIdQuery query) {
        return new AppointmentId(query.id());
    }

    public AppointmentId fromDeleteCommandToAppointmentId(final DeleteAppointmentCommand command) {
        return new AppointmentId(command.id());
    }
}
