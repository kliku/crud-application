package com.example.backend.model;

import lombok.Data;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "company")
@Data
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "company_nip")
    private String companyNip;
    @OneToMany(mappedBy = "company", cascade = CascadeType.REMOVE)
    private List<Employee> employeeList;
}