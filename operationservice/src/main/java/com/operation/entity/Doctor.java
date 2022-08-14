package com.operation.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.operation.util.GenderType;
import com.operation.util.SharedModel;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@EntityListeners(AuditingEntityListener.class)
@Table(name = "doctor", schema = "operation")
public class Doctor extends SharedModel {
    private Integer doctorId;
    private String firstName;
    private String lastName;
    private Integer genderTypeId;
    private Integer companyProfileId;
    private Integer addressBookId;

    private AddressBook addressBook;

    private String genderDescription;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DOCTOR_G1")
    @SequenceGenerator(name = "DOCTOR_G1", sequenceName = "doctor_id", schema = "operation", allocationSize = 1)
    @Column(name = "doctor_id", nullable = false, precision = 0, unique = true)
    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    @Basic
    @Column(name = "first_name", nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "gender_type_id", nullable = false)
    public Integer getGenderTypeId() {
        return genderTypeId;
    }

    public void setGenderTypeId(Integer genderTypeId) {
        this.genderTypeId = genderTypeId;
        if (genderTypeId != null) {
            this.setGenderDescription(GenderType.findOne(genderTypeId).getGenderDescription());
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
    public Integer getAddressBookId() {
        return addressBookId;
    }

    public void setAddressBookId(Integer addressBookId) {
        this.addressBookId = addressBookId;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_book_id")
    public AddressBook getAddressBook() {
        return addressBook;
    }

    public void setAddressBook(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    public String getGenderDescription() {
        return genderDescription;
    }

    public void setGenderDescription(String genderDescription) {
        this.genderDescription = genderDescription;
    }
}
