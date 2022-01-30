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

import javax.validation.Valid;
import java.util.List;

import static com.employee.management.constants.Messages.*;

/**
 * Employee Controller
 */
@Slf4j
@RestController
public class EmployeeController {
    /**
     * EmployeeService instance
     */
    private final EmployeeService employeeService;

    /**
     * ModelMapper instance
     */
    private final ModelMapper modelMapper;

    /**
     * Dependency injection
     *
     * @param employeeService
     * @param modelMapper
     */
    public EmployeeController(EmployeeService employeeService, ModelMapper modelMapper) {
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
    }

    /**
     * List employees
     *
     * @return
     */
    @GetMapping("/employees")
    public ResponseEntity<Object> listEmployees() {
        List<Employee> employees = employeeService.list();
        return RestResponseMapper.map(SUCCESS, HttpStatus.OK, employees, RECORDS_RECEIVED);
    }

    /**
     * Get employee by id
     *
     * @param id
     * @return
     */
    @GetMapping("/employee/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        Employee employee = employeeService.get(id);
        if (employee == null) {
            return RestResponseMapper.map(FAIL, HttpStatus.NOT_FOUND, null, NOT_FOUND);
        }
        return RestResponseMapper.map(SUCCESS, HttpStatus.OK, employee, RECORDS_RECEIVED);
    }

    /**
     * Create employee
     *
     * @param employeeRequest
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<Object> addEmployee(@RequestBody @Valid EmployeeRequest employeeRequest) {
        try {
            Employee employee = modelMapper.map(employeeRequest, Employee.class);
            employee = employeeService.save(employee);
            return RestResponseMapper.map(SUCCESS, HttpStatus.OK, employee, RECORD_CREATED);
        } catch (Exception e) {
            log.error(e.getMessage());
            return RestResponseMapper.map(FAIL, HttpStatus.INTERNAL_SERVER_ERROR, null, SERVER_ERROR);
        }

    }

    /**
     * Delete employee
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable Long id) {
        Employee employee = employeeService.get(id);

        if (employee == null) {
            return RestResponseMapper.map(FAIL, HttpStatus.NOT_FOUND, null, NOT_FOUND);
        }
        try {
            employeeService.delete(id);
            return RestResponseMapper.map(SUCCESS, HttpStatus.OK, null, RECORD_DELETED);
        } catch (Exception e) {
            log.error(e.getMessage());
            return RestResponseMapper.map(FAIL, HttpStatus.INTERNAL_SERVER_ERROR, null, SERVER_ERROR);
        }
    }

    /**
     * Update employee
     *
     * @param id
     * @param employeeRequest
     * @return
     */
    @PutMapping("update/{id}")
    public ResponseEntity<Object> updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeRequest employeeRequest) {
        Employee employee = employeeService.get(id);
        if (employee == null) {
            return RestResponseMapper.map(FAIL, HttpStatus.NOT_FOUND, null, NOT_FOUND);
        }
        try {
            employee = modelMapper.map(employeeRequest, Employee.class);
            employee = employeeService.update(employee, id);
            return RestResponseMapper.map(SUCCESS, HttpStatus.OK, employee, RECORD_UPDATED);
        } catch (Exception e) {
            log.error(e.getMessage());
            return RestResponseMapper.map(FAIL, HttpStatus.INTERNAL_SERVER_ERROR, null, SERVER_ERROR);
        }
    }
}
