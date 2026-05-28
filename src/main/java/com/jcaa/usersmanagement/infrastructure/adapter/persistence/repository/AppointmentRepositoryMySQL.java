package com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository;

import com.jcaa.usersmanagement.application.port.out.*;
import com.jcaa.usersmanagement.domain.exception.AppointmentNotFoundException;
import com.jcaa.usersmanagement.domain.model.AppointmentModel;
import com.jcaa.usersmanagement.domain.valueobject.AppointmentId;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.AppointmentPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.exception.PersistenceException;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper.AppointmentPersistenceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;
import java.sql.SQLException;

@Log
@RequiredArgsConstructor
public final class AppointmentRepositoryMySQL
        implements SaveAppointmentPort,
        UpdateAppointmentPort,
        GetAppointmentByIdPort,
        GetAllAppointmentsPort,
        DeleteAppointmentPort {

    private static final String SQL_INSERT =
            "INSERT INTO appointments "
            + "(id, patient_id, doctor_id, appointment_date, "
            + "appointment_reason, appointment_status, created_at, updated_at) "
            + "VALUES (?, ?, ?, ?, ?, ?, NOW(), NOW())";

    private static final String SQL_UPDATE =
            "UPDATE appointments SET "
            + "doctor_id = ?, "
            + "appointment_date = ?, "
            + "appointment_reason = ?, "
            + "appointment_status = ?, "
            + "updated_at = NOW() "
            + "WHERE id = ?";

    private static final String SQL_SELECT_BY_ID =
            "SELECT id, patient_id, doctor_id, appointment_date, "
            + "appointment_reason, appointment_status, created_at, updated_at "
            + "FROM appointments "
            + "WHERE id = ? LIMIT 1";

    private static final String SQL_SELECT_ALL =
            "SELECT id, patient_id, doctor_id, appointment_date, "
                    + "appointment_reason, appointment_status, created_at, updated_at "
                    + "FROM appointments "
                    + "ORDER BY appointment_date ASC";

    private static final String SQL_DELETE =
            "DELETE FROM appointments "
            + "WHERE id = ?";

    private final Connection connection;

    @Override
    public AppointmentModel save(final AppointmentModel appointment) {

        final AppointmentPersistenceDto dto =
                AppointmentPersistenceMapper.fromModelToDto(appointment);
        executeSave(dto);

        return findByIdOrFail(appointment.getId());
    }
    @Override
    public AppointmentModel update(final AppointmentModel appointment) {

        final AppointmentPersistenceDto dto =
                AppointmentPersistenceMapper.fromModelToDto(appointment);
        executeUpdate(dto);

        return findByIdOrFail(appointment.getId());
    }

    @Override
    public Optional<AppointmentModel> getById(
            final AppointmentId appointmentId
            ) {

        try (final PreparedStatement statement =
                connection.prepareStatement(SQL_SELECT_BY_ID)) {
            statement.setString(1, appointmentId.value());

            final ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()){
                return Optional.empty();
            }

            return Optional.of(
                    AppointmentPersistenceMapper.fromResultSetToModel(resultSet)
            );
        } catch (final SQLException exception) {

            throw PersistenceException.becauseFindByIdFailed(
                    "appointment",
                    appointmentId.value(),
                    exception
            );
        }
    }

    @Override
    public List<AppointmentModel> getAll() {

        try (final PreparedStatement statement =
                     connection.prepareStatement(SQL_SELECT_ALL)) {

            final ResultSet resultSet = statement.executeQuery();

            return AppointmentPersistenceMapper
                    .fromResultSetToModelList(resultSet);

        } catch (final SQLException exception) {

            throw PersistenceException.becauseFindAllFailed(
                    "appointments",
                    exception
            );
        }
    }

    @Override
    public void delete(final AppointmentId appointmentId) {

        try (final PreparedStatement statement =
                connection.prepareStatement(SQL_DELETE)) {
            statement.setString(1, appointmentId.value());
            statement.executeUpdate();
        } catch (final SQLException exception) {

            throw PersistenceException.becauseDeleteFailed(
                    "appointment",
                    appointmentId.value(),
                    exception
            );
        }
    }


    private void executeSave(final AppointmentPersistenceDto dto) {

        try (final PreparedStatement statement =
                     connection.prepareStatement(SQL_INSERT)) {

            statement.setString(1, dto.id());
            statement.setString(2, dto.patientId());
            statement.setString(3, dto.doctorId());
            statement.setString(4, dto.appointmentDate());
            statement.setString(5, dto.appointmentReason());
            statement.setString(6, dto.appointmentStatus());

            statement.executeUpdate();

        } catch (final SQLException exception) {

            throw PersistenceException.becauseSaveFailed(
                    "appointment",
                    dto.id(),
                    exception
            );
        }
    }

    private void executeUpdate(final AppointmentPersistenceDto dto) {

        try (final PreparedStatement statement =
                     connection.prepareStatement(SQL_UPDATE)) {

            statement.setString(1, dto.doctorId());
            statement.setString(2, dto.appointmentDate());
            statement.setString(3, dto.appointmentReason());
            statement.setString(4, dto.appointmentStatus());
            statement.setString(5, dto.id());

            statement.executeUpdate();

        } catch (final SQLException exception) {

            throw PersistenceException.becauseUpdateFailed(
                    "appointment",
                    dto.id(),
                    exception
            );
        }
    }

    private AppointmentModel findByIdOrFail(
            final AppointmentId appointmentId
    ) {

        return getById(appointmentId)
                .orElseThrow(() ->
                        AppointmentNotFoundException.becauseIdWasNotFound(
                                appointmentId.value()
                        )
                );
    }

}
