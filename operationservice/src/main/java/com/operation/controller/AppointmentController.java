package com.operation.controller;

import com.operation.entity.Appointment;
import com.operation.service.AppointmentService;
import com.operation.util.AppointmentStatus;
import com.operation.util.MasterDataStatus;
import com.operation.vo.AppointmentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("appointments")
public class AppointmentController {
    
    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity createAppointment(@Valid @RequestBody Appointment appointment) {
        return this.appointmentService.createAppointment(appointment);
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity updateAppointment(@Valid @RequestBody Appointment appointment) {
        return this.appointmentService.updateAppointment(appointment);
    }

    @DeleteMapping("{appointmentId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Appointment> deleteAppointment(@PathVariable("appointmentId") Integer appointmentId) {
        return this.appointmentService.deleteAppointment(appointmentId);
    }

    @PostMapping("/appointmentSearch")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public List<Appointment> appointmentSearch(@RequestBody AppointmentVo appointmentVo) {
        return this.appointmentService.appointmentSearch(appointmentVo);
    }

    @GetMapping("/appointmentStatus")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public List<AppointmentStatus> findAppointmentStatus() {
        return Arrays.asList(AppointmentStatus.values());
    }


    @GetMapping("/masterStatus")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public List<MasterDataStatus> findMasterStatus() {
        return Arrays.asList(MasterDataStatus.values());
    }

}
