package com.example.fetch_employees.service;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fetch_employees.config.ConfigProperties;
import com.example.fetch_employees.model.Employee;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class to manage employee-related operations.
 */
@Service
public class EmployeeService {

	@Autowired
	private ConfigProperties configProperties;
	
    // Sample employee list, you can replace it with a database later
    private List<Employee> employees = new ArrayList<>();

    // Adding some initial employees for testing
    public EmployeeService() {
        employees.add(new Employee(1, "John Doe", "Engineering"));
        employees.add(new Employee(2, "Jane Smith", "Marketing"));
        employees.add(new Employee(3, "Mike Brown", "Sales"));
    }

    /**
     * Fetches all the employees.
     * @return List of Employee objects
     */
    public List<Employee> getEmployees() {
        return employees;
    }

    /**
     * Adds a new employee to the list.
     * @param employee the employee to be added
     * @return the added employee
     */
    public Employee addEmployee(Employee employee) {
        employees.add(employee);
        return employee;
    }
    
    
    public List<Employee> getEmployeesForProfiles() {
        // Fetch the employee limit from the active profile's configuration.
        int limit = configProperties.getEmployeeLimit();
        List<Employee> employees = new ArrayList<>();

        // Create mock employee data up to the configured limit.
        for (int i = 1; i <= limit; i++) {
            employees.add(new Employee(i, "Employee " + i, "Department " + i));
        }

        // Return the list of employees as the response.
        return employees;
    }
}


