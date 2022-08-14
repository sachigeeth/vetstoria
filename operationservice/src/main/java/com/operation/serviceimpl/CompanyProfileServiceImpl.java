package com.operation.serviceimpl;

import com.operation.entity.CompanyProfile;
import com.operation.entity.QCompanyProfile;
import com.operation.repository.CompanyProfileRepository;
import com.operation.service.CompanyProfileService;
import com.operation.util.DateUtil;
import com.operation.util.MasterDataStatus;
import com.operation.vo.CompanyProfileVo;
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
public class CompanyProfileServiceImpl implements CompanyProfileService {

    private final CompanyProfileRepository companyProfileRepository;
    private final Logger logger = LoggerFactory.getLogger(CompanyProfileServiceImpl.class);


    @Autowired
    public CompanyProfileServiceImpl(CompanyProfileRepository companyProfileRepository) {
        this.companyProfileRepository = companyProfileRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ResponseEntity createCompanyProfile(CompanyProfile companyProfile) {
        ResponseEntity responseEntity;
        if (companyProfile.getAddressBook() != null) {
            companyProfile.getAddressBook().setStatus(MasterDataStatus.APPROVED.getStatusSeq());
        }
        this.companyProfileRepository.save(companyProfile);
        responseEntity = new ResponseEntity<>(companyProfile, HttpStatus.CREATED);
        return responseEntity;
    }

    @Override
    @Transactional()
    public ResponseEntity updateCompanyProfile(CompanyProfile companyProfile) {
        ResponseEntity<CompanyProfile> responseEntity;
        Optional<CompanyProfile> dbCompanyProfile = this.companyProfileRepository.findById(companyProfile.getCompanyProfileId());
        if (dbCompanyProfile.isPresent()) {
            this.companyProfileRepository.save(companyProfile);
            responseEntity = new ResponseEntity<>(companyProfile, HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>(companyProfile, HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @Override
    @Transactional()
    public ResponseEntity<CompanyProfile> deleteCompanyProfile(Integer companyProfileId) {
        Optional<CompanyProfile> dbCompanyProfile = this.companyProfileRepository.findById(companyProfileId);
        ResponseEntity<CompanyProfile> responseEntity;
        if (dbCompanyProfile.isPresent()) {
            dbCompanyProfile.get().setStatus(MasterDataStatus.DELETED.getStatusSeq());
            dbCompanyProfile.get().getAddressBook().setStatus(MasterDataStatus.DELETED.getStatusSeq());
            this.companyProfileRepository.save(dbCompanyProfile.get());
            responseEntity = new ResponseEntity<>(HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @Override
    public List<CompanyProfile> companyProfileSearch(CompanyProfileVo companyProfileVo) {
        List<CompanyProfile> companyProfileList = new ArrayList<>();
        try{
            QCompanyProfile qCompanyProfile = QCompanyProfile.companyProfile;
            BooleanBuilder builder = new BooleanBuilder();

            if (companyProfileVo.getCompanyName() != null) {
                builder.and(qCompanyProfile.companyName.containsIgnoreCase(companyProfileVo.getCompanyName()));
            }
            if (companyProfileVo.getShortCode() != null) {
                builder.and(qCompanyProfile.shortCode.containsIgnoreCase(companyProfileVo.getShortCode()));
            }
            if (companyProfileVo.getCountryId() != null) {
                builder.and(qCompanyProfile.addressBook.countryId.eq(companyProfileVo.getCountryId()));
            }
            if (companyProfileVo.getLocationId() != null) {
                builder.and(qCompanyProfile.addressBook.locationId.eq(companyProfileVo.getLocationId()));
            }
            if (companyProfileVo.getStatus() != null) {
                builder.and(qCompanyProfile.status.eq(companyProfileVo.getStatus()));
            }
            if (companyProfileVo.getCreatedFromDate() != null) {
                Date createdToDate = DateUtil.setTimeToDate(companyProfileVo.getCreatedFromDate(), 23, 59, 59);
                builder.and(qCompanyProfile.createdDate.after(createdToDate));
            }
            if (companyProfileVo.getCreatedToDate() != null) {
                Date createdToDate = DateUtil.setTimeToDate(companyProfileVo.getCreatedToDate(), 23, 59, 59);
                builder.and(qCompanyProfile.createdDate.before(createdToDate));
            }
            companyProfileList = (List<CompanyProfile>) this.companyProfileRepository.findAll(builder);
        }catch (Exception e){
            logger.error("Company Profile Search Error", e);
        }
        return companyProfileList;
    }
}
