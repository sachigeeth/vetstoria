package com.operation.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.operation.util.AppointmentStatus;
import com.operation.util.SharedModel;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@EntityListeners(AuditingEntityListener.class)
@Table(name = "appointment", schema = "operation")
public class Appointment extends SharedModel {
    private Integer appointmentId;
    private Integer customerId;
    private Integer doctorId;
    private Date appointmentDate;
    private Integer appointmentStatusId;
    private Integer companyProfileId;

    private String appointmentStatusDescription;

    private Customer customer;
    private Doctor doctor;

    private List<PetAppointmentMapping> petAppointmentMappings;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "APPOINTMENT_G1")
    @SequenceGenerator(name = "APPOINTMENT_G1", sequenceName = "appointment_id", schema = "operation", allocationSize = 1)
    @Column(name = "appointment_id", nullable = false, precision = 0, unique = true)
    public Integer getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    @Basic
    @Column(name = "customer_id", nullable = false)
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @Basic
    @Column(name = "doctor_id", nullable = false)
    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    @Basic
    @Column(name = "appointment_date", nullable = false)
    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    @Basic
    @Column(name = "appointmentStatus_id", nullable = false)
    public Integer getAppointmentStatusId() {
        return appointmentStatusId;
    }

    public void setAppointmentStatusId(Integer appointmentStatusId) {
        this.appointmentStatusId = appointmentStatusId;
        if (appointmentStatusId != null) {
            this.setAppointmentStatusDescription(AppointmentStatus.findOne(appointmentStatusId).getAppointmentStatusDescription());
        }
    }

    @Basic
    @Column(name = "company_profile_id", nullable = false)
    public Integer getCompanyProfileId() {
        return companyProfileId;
    }

    public void setCompanyProfileId(Integer companyProfileId) {
        this.companyProfileId = companyProfileId;
    }

    @Transient
    public String getAppointmentStatusDescription() {
        return appointmentStatusDescription;
    }

    public void setAppointmentStatusDescription(String appointmentStatusDescription) {
        this.appointmentStatusDescription = appointmentStatusDescription;
    }

    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", insertable = false, updatable = false)
    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_id", insertable = false, updatable = false)
    public List<PetAppointmentMapping> getPetAppointmentMappings() {
        return petAppointmentMappings;
    }

    public void setPetAppointmentMappings(List<PetAppointmentMapping> petAppointmentMappings) {
        this.petAppointmentMappings = petAppointmentMappings;
    }
}
