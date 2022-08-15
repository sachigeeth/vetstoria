package com.operation.repository;

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphQuerydslPredicateExecutor;
import com.operation.entity.PetAppointmentMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetAppointmentMappingRepository extends JpaRepository<PetAppointmentMapping, Integer>, EntityGraphJpaRepository<PetAppointmentMapping, Integer>, EntityGraphQuerydslPredicateExecutor<PetAppointmentMapping> {
}
