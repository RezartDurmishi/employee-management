package com.employee.management.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "employeeName")
    @JsonProperty("employee_name")
    private String name;


    @Column(name = "employeeSalary")
    @JsonProperty("employee_salary")
    private double salary;

    @Column(name = "employeeAge")
    @JsonProperty("employee_age")
    private int age;

}