package com.employee.management.controller;

import com.employee.management.entity.Employee;
import com.employee.management.mappers.RestResponseMapper;
import com.employee.management.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public ResponseEntity<Object> findAllEmployees() {
        return RestResponseMapper.generateResponse(
                "success",
                HttpStatus.OK,
                this.employeeService.getEmployees()
        );
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Object> getEmployeeById(@PathVariable Integer id) {
        return RestResponseMapper.generateResponse(
                "success",
                HttpStatus.OK,
                this.employeeService.getEmployeeById(id)
        );
    }

    @PostMapping("/create")
    public ResponseEntity<Object> addEmployee(@RequestBody Employee employee) {
        return RestResponseMapper.generateResponse(
                "success",
                HttpStatus.OK,
                this.employeeService.saveEmployee(employee)
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        return RestResponseMapper.generateResponse(
                "success",
                HttpStatus.OK,
                this.employeeService.deleteEmployee(id)
        );
    }


    @PutMapping("update/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Integer id, @RequestBody Employee employee) throws Exception {
        Employee existingEmployee = employeeService.getEmployeeById(id);

        if (existingEmployee == null) {
            throw new Exception("User Not Found");
        }
        existingEmployee.setName(employee.getName());
        existingEmployee.setSalary(employee.getSalary());
        existingEmployee.setAge(employee.getAge());

        return RestResponseMapper.generateResponse(
                "success",
                HttpStatus.OK,
                this.employeeService.updateEmployee(existingEmployee)
        );

    }
}
