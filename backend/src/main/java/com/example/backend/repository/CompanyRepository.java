package com.example.backend.repository;

import com.example.backend.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    @Query(value = "SELECT c FROM Company c WHERE lower(c.companyName) like :searchTxt or c.companyNip like :searchTxt")
    List<Company> findCompanyByTxt(String searchTxt);
}
