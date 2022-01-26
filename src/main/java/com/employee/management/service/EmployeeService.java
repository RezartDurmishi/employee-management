package com.employee.management.service;

import com.employee.management.entity.Employee;
import com.employee.management.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }


    public Employee saveEmployee(Employee employee) {
        return repository.save(employee);
    }

    public List<Employee> getEmployees() {
        return repository.findAll();
    }


    public Object deleteEmployee(Long id) {
      repository.deleteById(id);
        return id;
    }

    public Employee getEmployeeById(Long id) {
        return this.repository.findById(id).orElse(null);
    }

    public Employee updateEmployee(Employee employee) {
        return repository.save(employee);
    }
}



