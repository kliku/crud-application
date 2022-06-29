package com.example.backend.backend;

import com.example.backend.domain.*;
import com.example.backend.model.Company;
import com.example.backend.model.Employee;
import com.example.backend.repository.CompanyRepository;
import com.example.backend.repository.EmployeeRepository;
import com.example.backend.service.CompanyService;
import com.example.backend.service.EmployeeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class ServiceMockitoTest {

    private EmployeeService employeeService;
    private EmployeeRepository employeeRepository;
    private CompanyRepository companyRepository;
    private CompanyService companyService;

    private MockMvc mvc;

    @BeforeEach
    void setUp() {
        employeeRepository = mock(EmployeeRepository.class);
        companyRepository = mock(CompanyRepository.class);
        employeeService = new EmployeeService(employeeRepository, companyRepository);
        companyService = new CompanyService(companyRepository);
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(employeeRepository);
        Mockito.reset(companyRepository);
    }

    @Test
    void testCreateCompany() {
        //GIVEN
        Company company = new Company();
        company.setCompanyName("PKO");
        company.setCompanyNip("1234567890");
        CompanyAddForm companyAddForm = new CompanyAddForm();
        companyAddForm.setCompanyName("PKO");
        companyAddForm.setCompanyNip("1234567890");
        //WHEN
        when(companyRepository.save(company)).thenReturn(company);
        companyService.addCompany(companyAddForm);
        //THEN
        verify(companyRepository, times(1)).save(company);
    }

    @Test
    void testDeleteCompany() {
        //GIVEN
        doNothing().when(companyRepository).deleteById(anyLong());
        //WHEN
        companyService.deleteCompany(1L);
        //THEN
        verify(companyRepository, times(1)).deleteById(1L);
    }

    @Test
    void editCompanyTest() {
        //GIVEN
        Company company = new Company();
        company.setCompanyName("PKO");
        company.setCompanyNip("1234567890");
        Company companyAfterUpdate = new Company();
        companyAfterUpdate.setCompanyName("BANK");
        companyAfterUpdate.setCompanyNip("0987654321");
        CompanyUpdateForm companyUpdateForm = new CompanyUpdateForm();
        companyUpdateForm.setCompanyName("BANK");
        companyUpdateForm.setCompanyNip("0987654321");
        companyUpdateForm.setId(1L);
        //WHEN
        when(companyRepository.findById(1L)).thenReturn(Optional.of(company));
        when(companyRepository.save(companyAfterUpdate)).thenReturn(companyAfterUpdate);
        companyService.editCompany(companyUpdateForm);
        //THEN
        verify(companyRepository, times(1)).save(companyAfterUpdate);
    }

    @Test
    void CreateEmployeeTest() {
        //GIVEN
        Company company = new Company();
        company.setCompanyName("PKO");
        company.setCompanyNip("1234567890");
        company.setId(1L);
        Employee employee = new Employee();
        employee.setEmployeeName("Adam");
        employee.setEmployeeSurname("Kowalski");
        employee.setCompany(company);
        EmployeeAddForm employeeAddForm = new EmployeeAddForm();
        employeeAddForm.setEmployeeName("Adam");
        employeeAddForm.setEmployeeSurname("Kowalski");
        employeeAddForm.setCompanyId(1L);
        //WHEN
        when(companyRepository.findById(1L)).thenReturn(Optional.of(company));
        when(employeeRepository.save(employee)).thenReturn(employee);
        employeeService.addEmployee(employeeAddForm);
        //THEN
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    void whenDeleteEmployeeThenShouldResponseEntity() {
        //GIVEN
        doNothing().when(employeeRepository).deleteById(anyLong());
        //WHEN
        employeeService.deleteEmployee(1L);
        //THEN
        verify(employeeRepository, times(1)).deleteById(1L);
    }

    @Test
    void whenEditEmployeeThenShouldNewEditEmployee() {
        //GIVEN
        Employee employee = new Employee();
        employee.setEmployeeName("Jan");
        employee.setEmployeeSurname("Kowalski");
        Employee employeeAfterUpdate = new Employee();
        employeeAfterUpdate.setEmployeeName("Zbyszek");
        employeeAfterUpdate.setEmployeeSurname("Kwiatkowski");
        EmployeeUpdateForm employeeUpdateForm = new EmployeeUpdateForm();
        employeeUpdateForm.setId(1L);
        employeeUpdateForm.setEmployeeName("Zbyszek");
        employeeUpdateForm.setEmployeeSurname("Kwiatkowski");
        //WHEN
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(employeeRepository.save(employee)).thenReturn(employeeAfterUpdate);
        employeeService.editEmployee(employeeUpdateForm);
        verify(employeeRepository, times(1)).findById(1L);
        verify(employeeRepository, times(1)).save(employeeAfterUpdate);
    }
}
