package com.example.front;

public class EmployeeAddForm {

    private String employeeName;

    private String employeeSurname;

    private Long companyId;

    public EmployeeAddForm(String employeeName, String employeeSurname, Long companyId) {
        this.employeeName = employeeName;
        this.employeeSurname = employeeSurname;
        this.companyId = companyId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeSurname() {
        return employeeSurname;
    }

    public void setEmployeeSurname(String employeeSurname) {
        this.employeeSurname = employeeSurname;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    //    public EmployeeAddForm(String employeeName, String employeeSurname) {
//        this.employeeName = employeeName;
//        this.employeeSurname = employeeSurname;
//    }
//
//    public String getEmployeeName() {
//        return employeeName;
//    }
//
//    public void setEmployeeName(String employeeName) {
//        this.employeeName = employeeName;
//    }
//
//    public String getEmployeeSurname() {
//        return employeeSurname;
//    }
//
//    public void setEmployeeSurname(String employeeSurname) {
//        this.employeeSurname = employeeSurname;
//    }
}

