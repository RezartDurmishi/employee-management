package com.employee.management.controller;

import com.employee.management.entity.Employee;
import com.employee.management.mappers.RestResponseMapper;
import com.employee.management.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.employee.management.constants.Messages.*;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public ResponseEntity<Object> findAllEmployees() {
        List<Employee> employees = this.employeeService.getEmployees();
        return RestResponseMapper.map(SUCCESS, HttpStatus.OK, employees, RECORDS_RECEIVED);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Object> getEmployeeById(@PathVariable Long id) {
        Employee employee = this.employeeService.getEmployeeById(id);
        return RestResponseMapper.map(SUCCESS, HttpStatus.OK, employee, RECORDS_RECEIVED);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> addEmployee(@RequestBody Employee employee) {
        Employee employee1 = this.employeeService.saveEmployee(employee);
        return RestResponseMapper.map(SUCCESS, HttpStatus.OK, employee1, RECORD_CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        this.employeeService.deleteEmployee(id);
        return RestResponseMapper.map(SUCCESS, HttpStatus.OK, null, RECORD_DELETED);
    }


    @PutMapping("update/{id}")
    public ResponseEntity<Object> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) throws Exception {
        try {
            Employee existingEmployee = employeeService.getEmployeeById(id);
            existingEmployee.setName(employee.getName());
            existingEmployee.setSalary(employee.getSalary());
            existingEmployee.setAge(employee.getAge());

            Employee updateEmployee = this.employeeService.updateEmployee(existingEmployee);

            return RestResponseMapper.map(SUCCESS, HttpStatus.OK, updateEmployee, RECORD_UPDATED);
        } catch (Exception e) {
            return RestResponseMapper.map(FAIL, HttpStatus.NOT_FOUND, null, NOT_FOUND);

        }

    }
}
