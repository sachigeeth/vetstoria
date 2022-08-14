package com.operation.service;

import com.operation.entity.Appointment;
import com.operation.entity.Customer;
import com.operation.vo.AppointmentVo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AppointmentService {
    ResponseEntity createAppointment(Appointment appointment);

    ResponseEntity updateAppointment(Appointment appointment);

    ResponseEntity<Appointment> deleteAppointment(Integer appointmentId);

    List<Appointment> appointmentSearch(AppointmentVo appointmentVo);

}
