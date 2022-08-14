package com.operation.service;

import com.operation.entity.Doctor;
import com.operation.vo.DoctorVo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DoctorService {
    ResponseEntity createDoctor(Doctor doctor);

    ResponseEntity updateDoctor(Doctor doctor);

    ResponseEntity<Doctor> deleteDoctor(Integer doctorId);

    List<Doctor> getDoctorList(Integer companyProfileId);

    List<Doctor> doctorSearch(DoctorVo doctorVo);

}
