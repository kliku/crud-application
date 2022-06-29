package com.example.backend.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "employee")
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;
    @Column(name = "employee_name")
    private String employeeName;
    @Column(name = "employee_surname")
    private String employeeSurname;
    @ManyToOne()
    @JoinColumn(name = "company_id")
    private Company company;
}
