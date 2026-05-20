package com.jcaa.usersmanagement.domain.model;

import com.jcaa.usersmanagement.domain.enums.AppointmentStatus;
import com.jcaa.usersmanagement.domain.valueobject.AppointmentId;
import lombok.Value;
@Value
public class AppointmentModel {

    AppointmentId id;
   // PatientId patientId;  se habilitara cuando la clase relacionada sea creada
   // DoctorId doctorId;  se habilitara cuando la clase relacionda sea creada
    AppointmentDate appointmentDate;
    AppointmentReason appointmentReason;
    AppointmentStatus appointmentStatus;

    public static AppointmentModel create(
            final AppointmentId id,
           // final PatientId patientId,
           // final DoctorId doctorId,
            final AppointmentDate appointmentDate,
            final AppointmentReason appointmentReason) {
        return new AppointmentModel(id, appointmentDate, appointmentReason, AppointmentStatus.SCHEDULED);
    }

    public AppointmentModel completed() {
        return new AppointmentModel(id, appointmentDate, appointmentReason, AppointmentStatus.COMPLETED);
    }
    public AppointmentModel canceled() {
        return new AppointmentModel(id, appointmentDate, appointmentReason, AppointmentStatus.CANCELED);
    }
}
