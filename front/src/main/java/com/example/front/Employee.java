package com.example.front;

public class Employee {
    private Long id;
    private String employeeName;
    private String employeeSurname;



    public Employee(Long id, String employeeName, String employeeSurname) {
        this.id = id;
        this.employeeName = employeeName;
        this.employeeSurname = employeeSurname;
    }

    public Employee() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
