package com.employee.management.service;

import com.employee.management.employeeRepository.EmployeeRepository;
import com.employee.management.entity.Employee;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository repository;
    private static long employeeCount = 3;

    //    @Autowired
    //    private EmployeeRepository repository;
    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }


    public Employee saveEmployee(Employee employee) {
        if (employee.getId() == null) {
            employee.setId(++employeeCount);
        }
        return repository.save(employee);
    }

    public List<Employee> getEmployees() {
        return repository.findAll();
    }

}
