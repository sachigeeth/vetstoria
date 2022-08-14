package com.operation.serviceimpl;

import com.operation.entity.Appointment;
import com.operation.entity.PetAppointmentMapping;
import com.operation.entity.QAppointment;
import com.operation.repository.AppointmentRepository;
import com.operation.service.AppointmentService;
import com.operation.util.DateUtil;
import com.operation.util.MasterDataStatus;
import com.operation.vo.AppointmentVo;
import com.querydsl.core.BooleanBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final Logger logger = LoggerFactory.getLogger(AppointmentServiceImpl.class);

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public ResponseEntity createAppointment(Appointment appointment) {
        ResponseEntity responseEntity;
        this.appointmentRepository.save(appointment);
        responseEntity = new ResponseEntity<>(appointment, HttpStatus.CREATED);
        return responseEntity;
    }

    @Override
    public ResponseEntity updateAppointment(Appointment appointment) {
        ResponseEntity<Appointment> responseEntity;
        Optional<Appointment> dbAppointment = this.appointmentRepository.findById(appointment.getAppointmentId());
        if (dbAppointment.isPresent()) {
            this.appointmentRepository.save(appointment);
            responseEntity = new ResponseEntity<>(appointment, HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>(appointment, HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity<Appointment> deleteAppointment(Integer appointmentId) {
        Optional<Appointment> dbAppointment = this.appointmentRepository.findById(appointmentId);
        ResponseEntity<Appointment> responseEntity;
        if (dbAppointment.isPresent()) {
            dbAppointment.get().setStatus(MasterDataStatus.DELETED.getStatusSeq());
            for (PetAppointmentMapping petAppointmentMapping : dbAppointment.get().getPetAppointmentMappings()) {
                petAppointmentMapping.setStatus(MasterDataStatus.DELETED.getStatusSeq());
            }
            this.appointmentRepository.save(dbAppointment.get());
            responseEntity = new ResponseEntity<>(HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @Override
    public List<Appointment> appointmentSearch(AppointmentVo appointmentVo) {
        List<Appointment> appointmentList = new ArrayList<>();
        try{
            QAppointment qAppointment = QAppointment.appointment;
            BooleanBuilder builder = new BooleanBuilder();

            builder.and(qAppointment.companyProfileId.eq(appointmentVo.getCompanyProfileId()));

            if (appointmentVo.getCustomerId() != null) {
                builder.and(qAppointment.customerId.eq(appointmentVo.getCustomerId()));
            }

            if (appointmentVo.getDoctorId() != null) {
                builder.and(qAppointment.doctorId.eq(appointmentVo.getDoctorId()));
            }

            if (appointmentVo.getAppointmentStatusId() != null) {
                builder.and(qAppointment.appointmentStatusId.eq(appointmentVo.getAppointmentStatusId()));
            }

            if (appointmentVo.getStatus() != null) {
                builder.and(qAppointment.status.eq(appointmentVo.getStatus()));
            }
            if (appointmentVo.getAppointmentFromDate() != null) {
                Date appointmentFromDate = DateUtil.setTimeToDate(appointmentVo.getAppointmentFromDate(), 23, 59, 59);
                builder.and(qAppointment.appointmentDate.after(appointmentFromDate));
            }
            if (appointmentVo.getAppointmentToDate() != null) {
                Date appointmentToDate = DateUtil.setTimeToDate(appointmentVo.getAppointmentToDate(), 23, 59, 59);
                builder.and(qAppointment.appointmentDate.before(appointmentToDate));
            }
            appointmentList = (List<Appointment>) this.appointmentRepository.findAll(builder);
        }catch (Exception e){
            logger.error("Appointment Search Error", e);
        }
        return appointmentList;
    }
}
