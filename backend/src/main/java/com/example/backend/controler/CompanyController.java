package com.example.backend.controler;

import com.example.backend.domain.CompanyAddForm;
import com.example.backend.domain.CompanyDto;
import com.example.backend.service.CompanyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping(value = "/company")
    public ResponseEntity<String> addCompany(@RequestBody CompanyAddForm companyAddForm) {
        Long id = companyService.addCompany(companyAddForm);
        return ResponseEntity.ok("Company id: " + id + " is created");
    }

    @GetMapping("/company")
    public List<CompanyDto> getCompanyList(@RequestParam(required = false) String searchTxt) {
        return companyService.getCompanyList(searchTxt);
    }

    @DeleteMapping(value = "company/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id) {
        try {
            companyService.deleteCompany(id);
            return ResponseEntity.ok("Delete company id: " + id);
        } catch (Exception e) {
            log.error("Error during delete company id: " + id, e);
            return ResponseEntity.internalServerError().body("Not find company with id: " + id);
        }
    }
}