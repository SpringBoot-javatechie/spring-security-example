package com.javatechie.controller;

import com.javatechie.entity.Employee;
import com.javatechie.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    public static final String AUTHORITY_ROLE_HR = "hasAuthority('ROLE_HR')";
    @Autowired
    private EmployeeService service;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the javatechie ! . Your official credential already has been shared over email";
    }

    @PostMapping("/create")
//    @PreAuthorize(AUTHORITY_ROLE_HR)
    public Employee onboardNewEmployee(@RequestBody Employee employee) {
        return service.createNewEmployee(employee);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_HR') or hasAuthority('ROLE_MANAGER')" )
    public List<Employee> getAll() {
        return service.getEmployees();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_EMPLOYEE')")
    public Employee getEmployeeById(@PathVariable Integer id) {
        return service.getEmployee(id);
    }

    @PutMapping("/update")
    @PreAuthorize(AUTHORITY_ROLE_HR)
    public Employee updateRoles(@RequestBody Employee employee){
        return service.changeRoleOfEmployee(employee);
    }

    @GetMapping("/test")
    @PreAuthorize("hasAuthority('ROLE_EMPLOYEE')")
    public String test(Principal principal){
        return principal.getName();
    }
}
