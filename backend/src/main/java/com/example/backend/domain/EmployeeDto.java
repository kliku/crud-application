package com.example.backend.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeDto {
    private Long id;
    private String employeeName;
    private String employeeSurname;
}
