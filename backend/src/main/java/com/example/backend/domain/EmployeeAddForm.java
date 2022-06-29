package com.example.backend.domain;

import lombok.Data;

@Data
public class EmployeeAddForm {

    private String employeeName;
    private String employeeSurname;
    private Long companyId;
}
