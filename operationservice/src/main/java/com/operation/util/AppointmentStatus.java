package com.operation.util;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Arrays;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AppointmentStatus {

    TENTATIVE(1, "Tentative"),
    CONFIRM(2, "Confirm"),
    HOLD(3, "Hold"),
    CANCEL(4, "Cancel");

    private final Integer appointmentStatusId;
    private final String appointmentStatusDescription;

    AppointmentStatus(Integer appointmentStatusId, String appointmentStatusDescription) {
        this.appointmentStatusId = appointmentStatusId;
        this.appointmentStatusDescription = appointmentStatusDescription;
    }

    public Integer getAppointmentStatusId() {
        return appointmentStatusId;
    }

    public String getAppointmentStatusDescription() {
        return appointmentStatusDescription;
    }

    public static AppointmentStatus findOne(Integer appointmentStatusId) {
        return Arrays.stream(AppointmentStatus.values())
                .filter(x -> x.appointmentStatusId.equals(appointmentStatusId))
                .findFirst()
                .orElse(null);
    }

}
