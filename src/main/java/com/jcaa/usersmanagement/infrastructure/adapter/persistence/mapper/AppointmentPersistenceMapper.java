package com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper;

import com.jcaa.usersmanagement.domain.enums.AppointmentStatus;
import com.jcaa.usersmanagement.domain.model.AppointmentModel;
import com.jcaa.usersmanagement.domain.valueobject.*;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.AppointmentPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity.AppointmentEntity;
import lombok.experimental.UtilityClass;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class AppointmentPersistenceMapper {

    public AppointmentPersistenceDto fromModelToDto(final AppointmentModel appointment){

        return new AppointmentPersistenceDto(
                appointment.getId().value(),
                appointment.getPatientId().value(),
                appointment.getDoctorId().value(),
                appointment.getAppointmentDate().value().toString(),
                appointment.getAppointmentReason().value(),
                appointment.getAppointmentStatus().name(),
                null,
                null
        );
    }

    public AppointmentEntity fromResultSetToEntity(final ResultSet resultSet) throws SQLException {

        return new AppointmentEntity(
                resultSet.getString("id"),
                resultSet.getString("patient_id"),
                resultSet.getString("doctor_id"),
                resultSet.getString("appointment_date"),
                resultSet.getString("appointment_reason"),
                resultSet.getString("appointment_status"),
                resultSet.getString("created_at"),
                resultSet.getString("updated_at")
        );
    }

    public AppointmentModel fromEntityToModel(final AppointmentEntity entity){

        return new AppointmentModel(
                new AppointmentId(entity.id()),
                new PatientId(entity.patientId()),
                new DoctorId(entity.doctorId()),
                new AppointmentDate(LocalDateTime.parse(entity.appointmentDate().replace(" ", "T"))),
                new AppointmentReason(entity.appointmentReason()),
                AppointmentStatus.fromString(entity.appointmentStatus())
        );
    }

    public AppointmentModel fromResultSetToModel (final ResultSet resultSet) throws SQLException{

        return fromEntityToModel(fromResultSetToEntity(resultSet));
    }

    public List<AppointmentModel> fromResultSetToModelList(final ResultSet resultSet) throws SQLException{

        final List<AppointmentModel> appointments = new ArrayList<>();

        while (resultSet.next()) {
            appointments.add(fromResultSetToModel(resultSet));
        }
        return appointments;
    }
}
