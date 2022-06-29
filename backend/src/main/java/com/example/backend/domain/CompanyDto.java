package com.example.backend.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CompanyDto {
    private Long id;
    private String companyName;
    private String companyNip;
    private List<EmployeeDto> employeeDtoList;
}
