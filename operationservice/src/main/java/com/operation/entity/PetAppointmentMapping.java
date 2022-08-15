package com.operation.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.operation.util.AppointmentType;
import com.operation.util.SharedModel;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@EntityListeners(AuditingEntityListener.class)
@Table(name = "pet_appointment_mapping", schema = "operation")
public class PetAppointmentMapping extends SharedModel {
    private Integer petAppointmentMappingId;
    private Integer appointmentId;
    private Integer petId;
    private Integer appointmentTypeId;

    private String appointmentTypeDescription;

    private Pet pet;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PAM_G1")
    @SequenceGenerator(name = "PAM_G1", sequenceName = "pet_appointment_mapping_id", schema = "operation", allocationSize = 1)
    @Column(name = "pet_appointment_mapping_id", nullable = false, precision = 0, unique = true)
    public Integer getPetAppointmentMappingId() {
        return petAppointmentMappingId;
    }

    public void setPetAppointmentMappingId(Integer petAppointmentMappingId) {
        this.petAppointmentMappingId = petAppointmentMappingId;
    }

    @Basic
    @Column(name = "appointment_id", nullable = false)
    public Integer getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    @Basic
    @Column(name = "pet_id", nullable = false)
    public Integer getPetId() {
        return petId;
    }

    public void setPetId(Integer petId) {
        this.petId = petId;
    }

    @Basic
    @Column(name = "appointmentType_id", nullable = false)
    public Integer getAppointmentTypeId() {
        return appointmentTypeId;
    }

    public void setAppointmentTypeId(Integer appointmentTypeId) {
        this.appointmentTypeId = appointmentTypeId;
        if (appointmentTypeId != null) {
            this.setAppointmentTypeDescription(AppointmentType.findOne(appointmentTypeId).getAppointmentTypeDescription());
        }
    }

    @Transient
    public String getAppointmentTypeDescription() {
        return appointmentTypeDescription;
    }

    public void setAppointmentTypeDescription(String appointmentTypeDescription) {
        this.appointmentTypeDescription = appointmentTypeDescription;
    }

    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id", insertable = false, updatable = false)
    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }
}
