package com.employee.management.service;

import com.employee.management.employeeRepository.EmployeeRepository;
import com.employee.management.entity.Employee;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class EmployeeService {
    private final EmployeeRepository repository;

    /*
        @Autowired
        private EmployeeRepository repository;
    */
    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }


    public Employee saveEmployee(Employee employee) {
        return repository.save(employee);
    }

    public List<Employee> getEmployees() {
        return repository.findAll();
    }


    public Object deleteEmployee(Integer id) {
      repository.deleteById(id);
        return id;
    }

    public Employee getEmployeeById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public Employee updateEmployee(Employee employee) {
        return repository.save(employee);
    }
}



