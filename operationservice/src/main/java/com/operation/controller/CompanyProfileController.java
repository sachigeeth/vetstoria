package com.operation.controller;

import com.operation.entity.CompanyProfile;
import com.operation.service.CompanyProfileService;
import com.operation.vo.CompanyProfileVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("companyProfiles")
public class CompanyProfileController {
    
    private final CompanyProfileService companyProfileService;

    @Autowired
    public CompanyProfileController(CompanyProfileService companyProfileService) {
        this.companyProfileService = companyProfileService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity createCompanyProfile(@Valid @RequestBody CompanyProfile companyProfile) {
        return this.companyProfileService.createCompanyProfile(companyProfile);
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity updateCompanyProfile(@Valid @RequestBody CompanyProfile companyProfile) {
        return this.companyProfileService.updateCompanyProfile(companyProfile);
    }

    @DeleteMapping("{companyProfileId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CompanyProfile> deleteCompanyProfile(@PathVariable("companyProfileId") Integer companyProfileId) {
        return this.companyProfileService.deleteCompanyProfile(companyProfileId);
    }

    @PostMapping("/companyProfileSearch")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public List<CompanyProfile> companyProfileSearch(@RequestBody CompanyProfileVo companyProfileVo) {
        return this.companyProfileService.companyProfileSearch(companyProfileVo);
    }

}
