package com.employee.management.controller;

import com.employee.management.entity.Employee;
import com.employee.management.mappers.RestResponseMapper;
import com.employee.management.request.EmployeeRequest;
import com.employee.management.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.employee.management.constants.Messages.*;

@Slf4j
@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    private final ModelMapper modelMapper;


    public EmployeeController(EmployeeService employeeService, ModelMapper modelMapper) {
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/employees")
    public ResponseEntity<Object> findAllEmployees() {
        List<Employee> employees = this.employeeService.getEmployees();
        return RestResponseMapper.map(SUCCESS, HttpStatus.OK, employees, RECORDS_RECEIVED);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Object> getEmployeeById(@PathVariable Long id) {
        Employee employee = this.employeeService.getEmployeeById(id);
        if (employee != null) {
            return RestResponseMapper.map(SUCCESS, HttpStatus.OK, employee, RECORDS_RECEIVED);
        }
        return RestResponseMapper.map(FAIL, HttpStatus.NOT_FOUND, null, NOT_FOUND);

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
    public ResponseEntity<Object> updateEmployee(@PathVariable Long id, @RequestBody EmployeeRequest employeeRequest) {
        Employee employee = this.employeeService.getEmployeeById(id);
        if (employee == null) {
            return RestResponseMapper.map(FAIL, HttpStatus.NOT_FOUND, null, NOT_FOUND);
        }
        try {
            employee = modelMapper.map(employeeRequest, Employee.class);
            employee = this.employeeService.updateEmployee(employee, id);
            return RestResponseMapper.map(SUCCESS, HttpStatus.OK, employee, RECORD_UPDATED);
        } catch (Exception e) {
            log.error(e.getMessage());
            return RestResponseMapper.map(FAIL, HttpStatus.INTERNAL_SERVER_ERROR, null, INTERNAL_SERVER_ERROR);
        }
    }
}
