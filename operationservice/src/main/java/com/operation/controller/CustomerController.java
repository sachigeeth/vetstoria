package com.operation.controller;

import com.operation.entity.Customer;
import com.operation.service.CustomerService;
import com.operation.util.GenderType;
import com.operation.util.MasterDataStatus;
import com.operation.vo.CustomerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("customers")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity createCustomer(@Valid @RequestBody Customer customer) {
        return this.customerService.createCustomer(customer);
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity updateCustomer(@Valid @RequestBody Customer customer) {
        return this.customerService.updateCustomer(customer);
    }

    @DeleteMapping("{customerId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable("customerId") Integer customerId) {
        return this.customerService.deleteCustomer(customerId);
    }

    @GetMapping("/getCustomerData")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public Customer getCustomerData(@RequestParam("customerId") Integer customerId) {
        return this.customerService.getCustomerData(customerId);
    }

    @GetMapping("/getCustomerList")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public List<Customer> getCustomerList(@RequestParam("companyProfileId") Integer companyProfileId) {
        return this.customerService.getCustomerList(companyProfileId);
    }

    @PostMapping("/customerSearch")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public List<Customer> customerSearch(@RequestBody CustomerVo customerVo) {
        return this.customerService.customerSearch(customerVo);
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
