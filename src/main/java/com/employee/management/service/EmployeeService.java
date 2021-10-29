package com.employee.management.service;

import com.employee.management.employeeRepository.EmployeeRepository;
import com.employee.management.entity.Employee;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository repository;
    private static int employeeCount = 3;

    //    @Autowired
    //    private EmployeeRepository repository;
    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }


    public Employee saveEmployee(Employee employee) {
        if (employee.getId() == null) {
            employee.setId((long) ++employeeCount);
        }
        return repository.save(employee);
    }

    public List<Employee> getEmployees() {
        return repository.findAll();
    }

    public Employee getEmployeeById(int id) {
        for (Employee employee : getEmployees()) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        return null;
    }

    public void deleteEmployee(int id) {
       repository.deleteById(id);
    }


//    public Employee deleteEmployee(int id)
//    {
//        Optional<Employee> employee = repository.findById(Math.toIntExact(id));
//
//        if(employee.isPresent())
//        {
//            repository.deleteById(Math.toIntExact(id));
//        }
//
//        return null;
//    }

    public Employee updateEmployee(Employee employee) {
        //casting long to int
        long longId = employee.getId();
        int id = (int) longId;

        Employee existingEmployee = repository.findById(id).orElse(null);

        existingEmployee.setName(employee.getName());
        existingEmployee.setSalary(employee.getSalary());
        existingEmployee.setAge(employee.getAge());

        return repository.save(existingEmployee);
    }
}


//        repository.deleteById(id);
//        return "Employee deleted:" + id;

//    public Employee getEmployeeById(int id) {
//        return repository.findById(id).orElse(null);
//    }

//    public Employee getEmployeeById(int id) {
//        for (Employee employee : employees) {
//            if employee.getId() == id) {
//                return employee;
//            }
//        }
//        return null;



