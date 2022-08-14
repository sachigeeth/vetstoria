package com.operation.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.operation.util.SharedModel;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@EntityListeners(AuditingEntityListener.class)
@Table(name = "company_profile", schema = "operation")
public class CompanyProfile extends SharedModel {
    private Integer companyProfileId;
    private String companyName;
    private String shortCode;
    private String description;
    private Integer addressBookId;

    private AddressBook addressBook;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMPANY_G1")
    @SequenceGenerator(name = "COMPANY_G1", sequenceName = "company_profile_id", schema = "operation", allocationSize = 1)
    @Column(name = "company_profile_id", nullable = false, precision = 0, unique = true)
    public Integer getCompanyProfileId() {
        return companyProfileId;
    }

    public void setCompanyProfileId(Integer companyProfileId) {
        this.companyProfileId = companyProfileId;
    }

    @Basic
    @Column(name = "company_name", nullable = false)
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Basic
    @Column(name = "short_code", nullable = false)
    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
