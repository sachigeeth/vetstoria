package com.operation.serviceimpl;

import com.operation.entity.Customer;
import com.operation.entity.Pet;
import com.operation.entity.QCustomer;
import com.operation.repository.CustomerRepository;
import com.operation.service.CustomerService;
import com.operation.util.DateUtil;
import com.operation.util.MasterDataStatus;
import com.operation.vo.CustomerVo;
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
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ResponseEntity createCustomer(Customer customer) {
        ResponseEntity responseEntity;
        if (customer.getAddressBook() != null) {
            customer.getAddressBook().setStatus(MasterDataStatus.APPROVED.getStatusSeq());
        }
        this.customerRepository.save(customer);
        responseEntity = new ResponseEntity<>(customer, HttpStatus.CREATED);
        return responseEntity;
    }

    @Override
    @Transactional()
    public ResponseEntity updateCustomer(Customer customer) {
        ResponseEntity<Customer> responseEntity;
        Optional<Customer> dbCustomer = this.customerRepository.findById(customer.getCustomerId());
        if (dbCustomer.isPresent()) {
            this.customerRepository.save(customer);
            responseEntity = new ResponseEntity<>(customer, HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>(customer, HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @Override
    @Transactional()
    public ResponseEntity<Customer> deleteCustomer(Integer customerId) {
        Optional<Customer> dbCustomer = this.customerRepository.findById(customerId);
        ResponseEntity<Customer> responseEntity;
        if (dbCustomer.isPresent()) {
            dbCustomer.get().setStatus(MasterDataStatus.DELETED.getStatusSeq());
            dbCustomer.get().getAddressBook().setStatus(MasterDataStatus.DELETED.getStatusSeq());
            for (Pet pet : dbCustomer.get().getPets()) {
                pet.setStatus(MasterDataStatus.DELETED.getStatusSeq());
            }
            this.customerRepository.save(dbCustomer.get());
            responseEntity = new ResponseEntity<>(HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @Override
    public Customer getCustomerData(Integer customerId) {
        return this.customerRepository.findById(customerId).get();
    }

    @Override
    public List<Customer> getCustomerList(Integer companyProfileId) {
        return this.customerRepository.findByCompanyProfileIdAndStatus(companyProfileId, MasterDataStatus.APPROVED.getStatusSeq());
    }

    @Override
    public List<Customer> customerSearch(CustomerVo customerVo) {
        List<Customer> customerList = new ArrayList<>();
        try {
            QCustomer qCustomer = QCustomer.customer;
            BooleanBuilder builder = new BooleanBuilder();

            builder.and(qCustomer.companyProfileId.eq(customerVo.getCompanyProfileId()));

            if (customerVo.getFirstName() != null) {
                builder.and(qCustomer.firstName.containsIgnoreCase(customerVo.getFirstName()));
            }
            if (customerVo.getLastName() != null) {
                builder.and(qCustomer.lastName.containsIgnoreCase(customerVo.getLastName()));
            }

            if (customerVo.getGenderTypeId() != null) {
                builder.and(qCustomer.genderTypeId.eq(customerVo.getGenderTypeId()));
            }
            if (customerVo.getCountryId() != null) {
                builder.and(qCustomer.addressBook.countryId.eq(customerVo.getCountryId()));
            }
            if (customerVo.getLocationId() != null) {
                builder.and(qCustomer.addressBook.locationId.eq(customerVo.getLocationId()));
            }
            if (customerVo.getPetName() != null) {
                builder.and(qCustomer.pets.any().name.containsIgnoreCase(customerVo.getPetName()));
            }
            if (customerVo.getPetTypeId() != null) {
                builder.and(qCustomer.pets.any().petTypeId.eq(customerVo.getPetTypeId()));
            }
            if (customerVo.getPetBreedId() != null) {
                builder.and(qCustomer.pets.any().petBreedId.eq(customerVo.getPetBreedId()));
            }
            if (customerVo.getStatus() != null) {
                builder.and(qCustomer.status.eq(customerVo.getStatus()));
            }
            if (customerVo.getCreatedFromDate() != null) {
                Date createdToDate = DateUtil.setTimeToDate(customerVo.getCreatedFromDate(), 23, 59, 59);
                builder.and(qCustomer.createdDate.after(createdToDate));
            }
            if (customerVo.getCreatedToDate() != null) {
                Date createdToDate = DateUtil.setTimeToDate(customerVo.getCreatedToDate(), 23, 59, 59);
                builder.and(qCustomer.createdDate.before(createdToDate));
            }
            customerList = (List<Customer>) this.customerRepository.findAll(builder);
        } catch (Exception e) {
            logger.error("Customer Search Error : ", e.getMessage());
        }
        return customerList;
    }
}
