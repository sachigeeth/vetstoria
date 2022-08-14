package com.operation.vo;

import java.util.Date;

public class AppointmentVo {
    private Integer customerId;
    private Integer doctorId;
    private Integer appointmentStatusId;
    private Integer companyProfileId;
    private Integer status;
    private Date appointmentFromDate;
    private Date appointmentToDate;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public Date getAppointmentFromDate() {
        return appointmentFromDate;
    }

    public void setAppointmentFromDate(Date appointmentFromDate) {
        this.appointmentFromDate = appointmentFromDate;
    }

    public Date getAppointmentToDate() {
        return appointmentToDate;
    }

    public void setAppointmentToDate(Date appointmentToDate) {
        this.appointmentToDate = appointmentToDate;
    }

    public Integer getAppointmentStatusId() {
        return appointmentStatusId;
    }

    public void setAppointmentStatusId(Integer appointmentStatusId) {
        this.appointmentStatusId = appointmentStatusId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCompanyProfileId() {
        return companyProfileId;
    }

    public void setCompanyProfileId(Integer companyProfileId) {
        this.companyProfileId = companyProfileId;
    }

}
