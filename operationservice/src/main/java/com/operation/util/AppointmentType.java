package com.operation.util;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Arrays;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AppointmentType {

    CONSULTATION(1, "Consultation"),
    VACCINATION(2, "Vaccination"),
    HEALTH_CHECK(3, "Health Check");

    private final Integer appointmentTypeId;
    private final String appointmentTypeDescription;

    AppointmentType(Integer appointmentTypeId, String appointmentTypeDescription) {
        this.appointmentTypeId = appointmentTypeId;
        this.appointmentTypeDescription = appointmentTypeDescription;
    }

    public Integer getAppointmentTypeId() {
        return appointmentTypeId;
    }

    public String getAppointmentTypeDescription() {
        return appointmentTypeDescription;
    }

    public static AppointmentType findOne(Integer appointmentTypeId) {
        return Arrays.stream(AppointmentType.values())
                .filter(x -> x.appointmentTypeId.equals(appointmentTypeId))
                .findFirst()
                .orElse(null);
    }

}
