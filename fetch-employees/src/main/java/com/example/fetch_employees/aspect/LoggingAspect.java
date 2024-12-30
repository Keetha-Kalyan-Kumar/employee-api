package com.example.fetch_employees.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Aspect for logging method executions.
 */
@Aspect
@Component // Marking this class as a Spring bean
public class LoggingAspect {

    // Logger for logging information
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    /**
     * This method logs before the execution of any method in the EmployeeController class.
     * @param joinPoint the method being intercepted
     */
    @Before("execution(* com.example.fetch_employees.controller.EmployeeController.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        // Log the method name and parameters before the method is executed
        logger.info("Method {} is about to be called with arguments: {}", 
                joinPoint.getSignature().getName(), 
                joinPoint.getArgs());
    }

    
    /**
     * This method logs the return value of fetchEmployees() after it is executed.
     * @param result the return value of the method
     */
    @AfterReturning(value = "execution(* com.example.fetch_employees.controller.EmployeeController.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        // Log the response after the method execution
        logger.info("Method {} executed successfully with result: {}", 
                joinPoint.getSignature().getName(), 
                result);
    }
   
}
