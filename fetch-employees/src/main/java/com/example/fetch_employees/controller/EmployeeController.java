package com.example.fetch_employees.controller;






import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fetch_employees.model.Employee;
import com.example.fetch_employees.service.EmployeeService;



import java.util.List;


/**
 * REST Controller for Employee APIs.
 */
@Validated
@RestController
@RequestMapping("/api") // Base URL for the APIs
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * Endpoint to fetch employees.
     * @return ResponseEntity containing List of Employee objects and HTTP status
     */
    @GetMapping(value="/fetchEmployees",produces = "*/*")
    public ResponseEntity<List<Employee>> fetchEmployees() {
        List<Employee> employees = employeeService.getEmployees();

        // Return the list of employees wrapped in a ResponseEntity with HTTP 200 status
        return new ResponseEntity<>(employees, HttpStatus.CREATED);
    }
    
    @PostMapping(value ="/addEmployee",consumes = "application/json",produces = "application/xml")
    public ResponseEntity<Employee> addEmployee(@Validated @RequestBody Employee employee) {
        // Call the service to add the new employee
        Employee addedEmployee = employeeService.addEmployee(employee);

        // Return the added employee wrapped in a ResponseEntity with HTTP 201 status (created)
        return new ResponseEntity<>(addedEmployee, HttpStatus.CREATED);
    }
}
