package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper;

import com.jcaa.usersmanagement.application.service.dto.command.CreateAppointmentCommand;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteAppointmentCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateAppointmentCommand;
import com.jcaa.usersmanagement.application.service.dto.query.GetAppointmentByIdQuery;
import com.jcaa.usersmanagement.domain.model.AppointmentModel;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.AppointmentResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateAppointmentRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UpdateAppointmentRequest;

import java.time.LocalDateTime;
import java.util.List;

public final class AppointmentDesktopMapper {

    private AppointmentDesktopMapper() {}

    public static CreateAppointmentCommand toCreateCommand(final CreateAppointmentRequest request) {
        return new CreateAppointmentCommand(
                request.id(), request.patientId(), request.doctorId(), LocalDateTime.parse(request.appointmentDate()), request.appointmentReason()
        );
    }

    public static UpdateAppointmentCommand toUpdateCommand(
            final UpdateAppointmentRequest request
    ){
        return new UpdateAppointmentCommand(
                request.id(), request.doctorId(), LocalDateTime.parse(request.appointmentDate()), request.appointmentReason()
        );
    }

    public static DeleteAppointmentCommand toDeleteCommand(
            final String id
    ){
        return new DeleteAppointmentCommand(id);
    }

    public static GetAppointmentByIdQuery toGetByIdQuery( final String id){
        return new GetAppointmentByIdQuery(id);
    }

    public static AppointmentResponse toResponse(final AppointmentModel appointment) {
        return new AppointmentResponse(
                appointment.getId().value(),
                appointment.getPatientId().value(),
                appointment.getDoctorId().value(),
                appointment.getAppointmentDate().value().toString(),
                appointment.getAppointmentReason().value(),
                appointment.getAppointmentStatus().name()
        );
    }

    public static List<AppointmentResponse> toResponseList(final List<AppointmentModel> appointments) {
        return appointments.stream()
                .map(AppointmentDesktopMapper::toResponse)
                .toList();
    }
}
