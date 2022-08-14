package com.operation.vo;

import java.util.Date;

public class CompanyProfileVo {
    private String companyName;
    private String shortCode;
    private Integer countryId;
    private Integer locationId;
    private Integer status;
    private Date createdFromDate;
    private Date createdToDate;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreatedFromDate() {
        return createdFromDate;
    }

    public void setCreatedFromDate(Date createdFromDate) {
        this.createdFromDate = createdFromDate;
    }

    public Date getCreatedToDate() {
        return createdToDate;
    }

    public void setCreatedToDate(Date createdToDate) {
        this.createdToDate = createdToDate;
    }
}
