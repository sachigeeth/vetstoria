package com.operation.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.operation.util.PetBreed;
import com.operation.util.PetType;
import com.operation.util.SharedModel;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@EntityListeners(AuditingEntityListener.class)
@Table(name = "pet", schema = "operation")
public class Pet extends SharedModel {
    private Integer petId;
    private Integer customerId;
    private String name;
    private Integer petTypeId;
    private Integer petBreedId;
    private Date dob;

    private String petTypeDescription;
    private String petBreedDescription;

    private Customer customer;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PET_G1")
    @SequenceGenerator(name = "PET_G1", sequenceName = "pet_id", schema = "operation", allocationSize = 1)
    @Column(name = "pet_id", nullable = false, precision = 0, unique = true)
    public Integer getPetId() {
        return petId;
    }

    public void setPetId(Integer petId) {
        this.petId = petId;
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
    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "pet_type_id", nullable = false)
    public Integer getPetTypeId() {
        return petTypeId;
    }

    public void setPetTypeId(Integer petTypeId) {
        this.petTypeId = petTypeId;
        if (petTypeId != null) {
            this.setPetTypeDescription(PetType.findOne(petTypeId).getPetTypeDescription());
        }
    }

    @Basic
    @Column(name = "pet_breed_id", nullable = false)
    public Integer getPetBreedId() {
        return petBreedId;
    }

    public void setPetBreedId(Integer petBreedId) {
        this.petBreedId = petBreedId;
        if (petBreedId != null) {
            this.setPetBreedDescription(PetBreed.findOne(petBreedId).getPetBreedDescription());
        }
    }

    @Basic
    @Column(name = "dob")
    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    @Transient
    public String getPetTypeDescription() {
        return petTypeDescription;
    }

    public void setPetTypeDescription(String petTypeDescription) {
        this.petTypeDescription = petTypeDescription;
    }


    @Transient
    public String getPetBreedDescription() {
        return petBreedDescription;
    }

    public void setPetBreedDescription(String petBreedDescription) {
        this.petBreedDescription = petBreedDescription;
    }

    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
