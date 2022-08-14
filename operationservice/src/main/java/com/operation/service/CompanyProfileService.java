package com.operation.service;

import com.operation.entity.CompanyProfile;
import com.operation.entity.Customer;
import com.operation.vo.CompanyProfileVo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CompanyProfileService {
    ResponseEntity createCompanyProfile(CompanyProfile companyProfile);

    ResponseEntity updateCompanyProfile(CompanyProfile companyProfile);

    ResponseEntity<CompanyProfile> deleteCompanyProfile(Integer companyProfileId);

    List<CompanyProfile> companyProfileSearch(CompanyProfileVo companyProfileVo);
}
