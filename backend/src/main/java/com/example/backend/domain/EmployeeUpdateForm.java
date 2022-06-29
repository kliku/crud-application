package com.example.backend.domain;

import lombok.Data;

@Data
public class EmployeeUpdateForm extends EmployeeAddForm {
    private Long id;
}
