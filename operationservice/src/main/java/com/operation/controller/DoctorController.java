package com.operation.controller;

import com.operation.entity.Doctor;
import com.operation.service.DoctorService;
import com.operation.util.GenderType;
import com.operation.util.MasterDataStatus;
import com.operation.vo.DoctorVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("doctors")
public class DoctorController {

    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity createDoctor(@Valid @RequestBody Doctor doctor) {
        return this.doctorService.createDoctor(doctor);
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity updateDoctor(@Valid @RequestBody Doctor doctor) {
        return this.doctorService.updateDoctor(doctor);
    }

    @GetMapping("/getDoctorList")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public List<Doctor> getDoctorList(@RequestParam("companyProfileId") Integer companyProfileId) {
        return this.doctorService.getDoctorList(companyProfileId);
    }

    @DeleteMapping("{doctorId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Doctor> deleteDoctor(@PathVariable("doctorId") Integer doctorId) {
        return this.doctorService.deleteDoctor(doctorId);
    }

    @PostMapping("/doctorSearch")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public List<Doctor> doctorSearch(@RequestBody DoctorVo doctorVo) {
        return this.doctorService.doctorSearch(doctorVo);
    }

    @GetMapping("/genderType")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public List<GenderType> findGenderType() {
        return Arrays.asList(GenderType.values());
    }


    @GetMapping("/masterStatus")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public List<MasterDataStatus> findMasterStatus() {
        return Arrays.asList(MasterDataStatus.values());
    }


}