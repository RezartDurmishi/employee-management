package com.employee.management.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class EmployeeRequest {

    @NotBlank(message = "Employee name is required!")
    private String name;

    @NotBlank(message = "Employee salary is required!")
    private double salary;

    @NotBlank(message = "Employee age is required!")
    private int age;
}
