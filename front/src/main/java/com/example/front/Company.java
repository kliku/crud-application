package com.example.front;

import java.util.List;

public class Company {

    private Long id;
    private String companyName;
    private String companyNip;
    private List<Employee> employeeDtoList;

    public Company() {
    }

    public Long getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyNip() {
        return companyNip;
    }

    public List<Employee> getEmployeeDtoList() {
        return employeeDtoList;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setCompanyNip(String companyNip) {
        this.companyNip = companyNip;
    }

    public void setEmployeeDtoList(List<Employee> employeeDtoList) {
        this.employeeDtoList = employeeDtoList;
    }
}