package com.example.fetch_employees.controller;






import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fetch_employees.config.ConfigProperties;
import com.example.fetch_employees.exception.EmployeeNotFoundException;
import com.example.fetch_employees.model.Employee;
import com.example.fetch_employees.service.EmployeeService;

import java.io.IOException;
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
    
    @Autowired
    private ConfigProperties configProperties;

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
    
    @GetMapping(value="/test",produces = "*/*")
    public ResponseEntity<String> testingVelocity() throws IOException{
    	employeeService.logboxTestSplit();
    	return new ResponseEntity<>("S",HttpStatus.OK);
    	
    }

    @GetMapping(value = "/string",produces = "*/*")
    public ResponseEntity<String> testingString(){
        String ans = employeeService.learningStringConcepts();
        return new ResponseEntity<>(ans,HttpStatus.OK);
    }
    
    
    @GetMapping(value = "/thread",produces = "*/*")
    public ResponseEntity<String> learningMultiThreading(){
         employeeService.conceptOfMultithreading();
        return new ResponseEntity<>("S",HttpStatus.OK);
    }
    
    @PostMapping(value ="/addEmployee",consumes = "application/json",produces = "application/xml")
    public ResponseEntity<Employee> addEmployee(@Validated @RequestBody Employee employee) {
        // Call the service to add the new employee
        Employee addedEmployee = employeeService.addEmployee(employee);

        // Return the added employee wrapped in a ResponseEntity with HTTP 201 status (created)
        return new ResponseEntity<>(addedEmployee, HttpStatus.CREATED);
    }
    
    
    @GetMapping("/{id}")
    public ResponseEntity<Employee> fetchEmployeeById(@PathVariable("id") int pk){
    	if(pk==100) {
    		throw new EmployeeNotFoundException("Employee with id : "+pk+" is not found");
    	}
    	
    	else if (pk < 0) {
            throw new IllegalArgumentException("ID cannot be negative");
        }
    	else if (pk > 100) {
            throw new RuntimeException("ID exceeds the limit");
        }
    	else {
    		return new ResponseEntity<>(new Employee(pk,"Employee Exception Testig","Department"),HttpStatus.OK);
    	}
    }
    
    
    @GetMapping("/fetch")
    public ResponseEntity<List<Employee>> getEmployeesForProfiles(){
    	List<Employee> ans = employeeService.getEmployeesForProfiles();
    	return new ResponseEntity<>(ans,HttpStatus.CREATED);
    }
    
    @GetMapping("/environment") // Maps HTTP GET requests to "/employees/environment".
    public ResponseEntity<String> getEnvironment() {
        // Fetch the environment name from the active profile's configuration.
        return new ResponseEntity<>("Running in: " + configProperties.getEnvironment(),HttpStatus.OK);
    }
}
