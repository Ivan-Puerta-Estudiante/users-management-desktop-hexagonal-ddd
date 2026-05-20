package com.jcaa.usersmanagement.domain.model;

import com.jcaa.usersmanagement.domain.enums.AppointmentStatus;
import com.jcaa.usersmanagement.domain.valueobject.AppointmentId;
import lombok.Value;
@Value
public class AppointmentModel {

    AppointmentId id;
    PatientName patientName;
    DoctorName doctorName;
    AppointmentDate appointmentDate;
    AppointmentReason appointmentReason;
    AppointmentStatus appointmentStatus;

    public static AppointmentModel create(
            final AppointmentId id,
            final PatientName patientName,
            final DoctorName doctorName,
            final AppointmentDate appointmentDate,
            final AppointmentReason appointmentReason) {
        return new AppointmentModel(id, patientName, doctorName, appointmentDate, appointmentReason, AppointmentStatus.SCHEDULED);
    }

    public AppointmentModel completed() {
        return new AppointmentModel(id, patientName, doctorName, appointmentDate, appointmentReason, AppointmentStatus.COMPLETED);
    }
    public AppointmentModel canceled() {
        return new AppointmentModel(id, patientName, doctorName, appointmentDate, appointmentReason, AppointmentStatus.CANCELED);
    }
}
