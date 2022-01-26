package com.employee.management.repository;

import com.employee.management.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Employee Repository
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
