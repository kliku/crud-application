package com.example.backend.controler;

import com.example.backend.domain.EmployeeAddForm;
import com.example.backend.domain.EmployeeDto;
import com.example.backend.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@Slf4j
public class EmployeeController {


    private final EmployeeService employeeService;

    @GetMapping(value = "/employee")
    public List<EmployeeDto> getEmployeeList() {
        return employeeService.getEmployeeList();
    }

    @PostMapping(value = "/employee")
    public ResponseEntity<String> addEmployee(@RequestBody EmployeeAddForm employeeAddForm) {
        try {
            Long id = employeeService.addEmployee(employeeAddForm);
            return ResponseEntity.ok("Employee id: " + id + " is created");
        } catch (ApplicationContextException e) {
            log.error("Error during add employee for company id: " + employeeAddForm.getCompanyId(), e);
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/employee/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        try {
            employeeService.deleteEmployee(id);
            return ResponseEntity.ok("Delete employee id: " + id);
        } catch (Exception e) {
            log.error("Error during delete employee id: " + id, e);
            return ResponseEntity.internalServerError().body("Not find employee with id: " + id);
        }
    }
}
