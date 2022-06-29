package com.example.backend.service;

import com.example.backend.domain.CompanyAddForm;
import com.example.backend.domain.CompanyDto;
import com.example.backend.domain.CompanyUpdateForm;
import com.example.backend.domain.EmployeeDto;
import com.example.backend.model.Company;
import com.example.backend.model.Employee;
import com.example.backend.repository.CompanyRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CompanyService {


    private final CompanyRepository companyRepository;

    public Long addCompany(CompanyAddForm companyAddForm) {
        Company company = new Company();
        company.setCompanyName(companyAddForm.getCompanyName());
        company.setCompanyNip(companyAddForm.getCompanyNip());
        companyRepository.save(company);
        log.info("Add company with succeed");
        return company.getId();
    }

    public void editCompany(CompanyUpdateForm companyUpdateForm) {
        Company company = companyRepository.findById(companyUpdateForm.getId()).orElseThrow(
                () -> new ApplicationContextException("Not find company"));
        company.setCompanyName(companyUpdateForm.getCompanyName());
        company.setCompanyNip(companyUpdateForm.getCompanyNip());
        companyRepository.save(company);
    }

    public List<CompanyDto> getCompanyList(String searchTxt) {
        List<Company> companyList;
        if (searchTxt != null && !searchTxt.isBlank()) {
            companyList = companyRepository.findCompanyByTxt("%" + searchTxt.toLowerCase() + "%");
        } else {
            companyList = companyRepository.findAll();
        }
        List<CompanyDto> resultList = companyList.stream()
                .map(company -> CompanyDto.builder().id(company.getId())
                        .companyName(company.getCompanyName())
                        .companyNip(company.getCompanyNip()).employeeDtoList(mapEmployeeDto(company.getEmployeeList()))
                        .build()).collect(Collectors.toList());
        return resultList;
    }

    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }

    private List<EmployeeDto> mapEmployeeDto(List<Employee> employeeList) {
        return employeeList.stream()
                .map(employee -> EmployeeDto.builder().id(employee.getId())
                        .employeeName(employee.getEmployeeName()).employeeSurname(employee.getEmployeeSurname()).build())
                .collect(Collectors.toList());
    }
}