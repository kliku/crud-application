package com.example.backend.service;


import com.example.backend.domain.EmployeeAddForm;
import com.example.backend.domain.EmployeeDto;
import com.example.backend.domain.EmployeeUpdateForm;
import com.example.backend.model.Company;
import com.example.backend.model.Employee;
import com.example.backend.repository.CompanyRepository;
import com.example.backend.repository.EmployeeRepository;
import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
@Slf4j
public class EmployeeService {


    private final EmployeeRepository employeeRepository;
    private final CompanyRepository companyRepository;


    public List<EmployeeDto> getEmployeeList() {
        List<Employee> employeeList = employeeRepository.findAll();
        List<EmployeeDto> resultList = employeeList.stream()
                .map(employee -> EmployeeDto.builder().id(employee.getId())
                        .employeeName(employee.getEmployeeName())
                        .employeeSurname(employee.getEmployeeSurname())
                        .build()).collect(Collectors.toList());
        return resultList;
    }

    public Long addEmployee(EmployeeAddForm employeeAddForm) {
        Employee employee = new Employee();
        employee.setEmployeeName(employeeAddForm.getEmployeeName());
        employee.setEmployeeSurname(employeeAddForm.getEmployeeSurname());
        Company company = companyRepository.findById(employeeAddForm.getCompanyId()).orElseThrow(
                () -> new ApplicationContextException("Not find company"));
        employee.setCompany(company);
        employeeRepository.save(employee);
        log.info("Add employee with succeed");
        return employee.getId();
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    public void editEmployee(EmployeeUpdateForm employeeUpdateForm) {
        Employee employee = employeeRepository.findById(employeeUpdateForm.getId()).orElseThrow(
                () -> new ApplicationContextException("Not find employee"));
        employee.setEmployeeName(employeeUpdateForm.getEmployeeName());
        employee.setEmployeeSurname(employeeUpdateForm.getEmployeeSurname());
        employeeRepository.save(employee);
    }

}
