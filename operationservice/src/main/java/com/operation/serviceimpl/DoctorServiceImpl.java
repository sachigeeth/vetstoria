package com.operation.serviceimpl;

import com.operation.entity.Doctor;
import com.operation.entity.QDoctor;
import com.operation.repository.DoctorRepository;
import com.operation.service.DoctorService;
import com.operation.util.DateUtil;
import com.operation.util.MasterDataStatus;
import com.operation.vo.DoctorVo;
import com.querydsl.core.BooleanBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final Logger logger = LoggerFactory.getLogger(DoctorServiceImpl.class);

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ResponseEntity createDoctor(Doctor doctor) {
        ResponseEntity responseEntity;
        if (doctor.getAddressBook() != null) {
            doctor.getAddressBook().setStatus(MasterDataStatus.APPROVED.getStatusSeq());
        }
        this.doctorRepository.save(doctor);
        responseEntity = new ResponseEntity<>(doctor, HttpStatus.CREATED);
        return responseEntity;
    }

    @Override
    @Transactional()
    public ResponseEntity updateDoctor(Doctor doctor) {
        ResponseEntity<Doctor> responseEntity;
        Optional<Doctor> dbDoctor = this.doctorRepository.findById(doctor.getDoctorId());
        if (dbDoctor.isPresent()) {
            this.doctorRepository.save(doctor);
            responseEntity = new ResponseEntity<>(doctor, HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>(doctor, HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @Override
    @Transactional()
    public ResponseEntity<Doctor> deleteDoctor(Integer doctorId) {
        Optional<Doctor> dbDoctor = this.doctorRepository.findById(doctorId);
        ResponseEntity<Doctor> responseEntity;
        if (dbDoctor.isPresent()) {
            dbDoctor.get().setStatus(MasterDataStatus.DELETED.getStatusSeq());
            dbDoctor.get().getAddressBook().setStatus(MasterDataStatus.DELETED.getStatusSeq());
            this.doctorRepository.save(dbDoctor.get());
            responseEntity = new ResponseEntity<>(HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @Override
    public List<Doctor> getDoctorList(Integer companyProfileId) {
        return this.doctorRepository.findByCompanyProfileIdAndStatus(companyProfileId, MasterDataStatus.APPROVED.getStatusSeq());
    }

    @Override
    public List<Doctor> doctorSearch(DoctorVo doctorVo) {
        List<Doctor> doctorList = new ArrayList<>();
        try{
            QDoctor qDoctor = QDoctor.doctor;
            BooleanBuilder builder = new BooleanBuilder();

            builder.and(qDoctor.companyProfileId.eq(doctorVo.getCompanyProfileId()));

            if (doctorVo.getFirstName() != null) {
                builder.and(qDoctor.firstName.containsIgnoreCase(doctorVo.getFirstName()));
            }
            if (doctorVo.getLastName() != null) {
                builder.and(qDoctor.lastName.containsIgnoreCase(doctorVo.getLastName()));
            }
            if (doctorVo.getGenderTypeId() != null) {
                builder.and(qDoctor.genderTypeId.eq(doctorVo.getGenderTypeId()));
            }
            if (doctorVo.getCountryId() != null) {
                builder.and(qDoctor.addressBook.countryId.eq(doctorVo.getCountryId()));
            }
            if (doctorVo.getLocationId() != null) {
                builder.and(qDoctor.addressBook.locationId.eq(doctorVo.getLocationId()));
            }
            if (doctorVo.getStatus() != null) {
                builder.and(qDoctor.status.eq(doctorVo.getStatus()));
            }
            if (doctorVo.getCreatedFromDate() != null) {
                Date createdToDate = DateUtil.setTimeToDate(doctorVo.getCreatedFromDate(), 23, 59, 59);
                builder.and(qDoctor.createdDate.after(createdToDate));
            }
            if (doctorVo.getCreatedToDate() != null) {
                Date createdToDate = DateUtil.setTimeToDate(doctorVo.getCreatedToDate(), 23, 59, 59);
                builder.and(qDoctor.createdDate.before(createdToDate));
            }
            doctorList = (List<Doctor>) this.doctorRepository.findAll(builder);
        }catch (Exception e){
            logger.error("Doctor Search Error : ", e.getMessage());
        }
        return doctorList;
    }
}
