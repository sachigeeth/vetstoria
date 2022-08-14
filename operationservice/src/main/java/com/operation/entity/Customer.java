package com.operation.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.operation.util.GenderType;
import com.operation.util.SharedModel;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@EntityListeners(AuditingEntityListener.class)
@Table(name = "customer", schema = "operation")
public class Customer extends SharedModel {
    private Integer customerId;
    private String firstName;
    private String lastName;
    private String occupation;
    private Integer genderTypeId;
    private Integer companyProfileId;
    private Integer addressBookId;

    private AddressBook addressBook;

    private String genderDescription;

    private List<Pet> pets;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUSTOMER_G1")
    @SequenceGenerator(name = "CUSTOMER_G1", sequenceName = "customer_id", schema = "operation", allocationSize = 1)
    @Column(name = "customer_id", nullable = false, precision = 0, unique = true)
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
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
    @Column(name = "occupation")
    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
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

    @Transient
    public String getGenderDescription() {
        return genderDescription;
    }

    public void setGenderDescription(String genderDescription) {
        this.genderDescription = genderDescription;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_book_id")
    public AddressBook getAddressBook() {
        return addressBook;
    }

    public void setAddressBook(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }
}
