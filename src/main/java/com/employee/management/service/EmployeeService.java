package com.employee.management.service;

import com.employee.management.entity.Employee;
import com.employee.management.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Employee Service
 */
@Service
public class EmployeeService {
    /**
     * EmployeeRepository instance
     */
    private final EmployeeRepository repository;

    /**
     * Dependency injection
     *
     * @param repository
     */
    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    /**
     * List employees
     *
     * @return
     */
    public List<Employee> list() {
        return repository.findAll();
    }

    /**
     * Get employee by id
     *
     * @param id
     * @return
     */
    public Employee get(Long id) {
        return this.repository.findById(id).orElse(null);
    }

    /**
     * Save employee
     *
     * @param employee
     * @return
     */
    public Employee save(Employee employee) {
        return repository.save(employee);
    }

    /**
     * Delete employee
     *
     * @param id
     */
    public void delete(Long id) {
        repository.deleteById(id);
    }

    /**
     * Update employee
     *
     * @param employee
     * @param id
     * @return
     */
    public Employee update(Employee employee, Long id) {
        employee.setId(id);
        return repository.save(employee);
    }
}



