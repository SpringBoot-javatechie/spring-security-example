package com.javatechie.service;

import com.javatechie.entity.Employee;
import com.javatechie.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    public Employee createNewEmployee(Employee employee) {
        return repository.save(employee);
    }

    public List<Employee> getEmployees() {
        return repository.findAll();
    }

    public Employee getEmployee(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee Not Found with id " + id));
    }
}
