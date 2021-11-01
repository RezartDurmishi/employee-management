package com.employee.management.controller;

import com.employee.management.entity.Employee;
import com.employee.management.mappers.RestResponseMapper;
import com.employee.management.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/create")
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @GetMapping("/employee/{id}")
    public Employee getEmployeeById(@PathVariable Integer id) {
        return employeeService.getEmployeeById(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {

        try {
            employeeService.deleteEmployee(id);
        } catch (Exception e) {
//            log.error(e.getMessage());
            return ResponseEntity.accepted().body(e.getMessage());
        }

        return ResponseEntity.accepted().body("User Deleted Successfully");
    }


    @PutMapping("update/{id}")
    public Employee updateEmployee(@PathVariable Integer id, @RequestBody Employee employee) throws Exception {
        Employee existingEmployee = employeeService.getEmployeeById(id);
        if (existingEmployee == null) {
            throw new Exception("User Not Found");
        }
        existingEmployee.setName(employee.getName());
        existingEmployee.setSalary(employee.getSalary());
        existingEmployee.setAge(employee.getAge());

        return employeeService.updateEmployee(existingEmployee);
    }
}
