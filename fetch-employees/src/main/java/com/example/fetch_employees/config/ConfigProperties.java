package com.example.fetch_employees.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * A configuration class to fetch profile-specific properties.
 */
@Component // Marks this class as a Spring-managed component.
public class ConfigProperties {

    // Fetches the "app.environment" value from the active profile.
    @Value("${app.environment}")
    private String environment;

    // Fetches the "app.feature.employee.limit" value from the active profile.
    @Value("${app.feature.employee.limit}")
    private int employeeLimit;

    // Getter for the environment name.
    public String getEnvironment() {
        return environment;
    }

    // Getter for the employee limit.
    public int getEmployeeLimit() {
        return employeeLimit;
    }
}