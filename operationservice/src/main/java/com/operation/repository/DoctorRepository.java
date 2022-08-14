package com.operation.repository;

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphQuerydslPredicateExecutor;
import com.operation.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer>, EntityGraphJpaRepository<Doctor, Integer>, EntityGraphQuerydslPredicateExecutor<Doctor> {

    List<Doctor> findByCompanyProfileIdAndStatus(Integer companyProfileId, Integer status);
}
