package com.employee.management.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * DTO class
 */
@Data
public class EmployeeRequest {

    @NotEmpty(message = "Employee name is required!")
    private String name;

    @NotNull(message = "Employee salary is required!")
    private BigDecimal salary;

    @NotNull(message = "Employee age is required!")
    private Long age;
}
