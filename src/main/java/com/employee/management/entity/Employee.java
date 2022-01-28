package com.employee.management.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Employee entity.
 */
@Entity
@Getter
@Setter
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employeeName", nullable = false)
    @JsonProperty("employee_name")
    private String name;


    @Column(name = "employeeSalary", nullable = false)
    @JsonProperty("employee_salary")
    private double salary;

    @Column(name = "employeeAge", nullable = false)
    @JsonProperty("employee_age")
    private int age;

}