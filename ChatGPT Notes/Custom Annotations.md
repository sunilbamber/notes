Custom annotations in Spring Boot allow developers to encapsulate logic and behavior into reusable annotations, making code more modular and easier to maintain. Below are examples of creating and using custom annotations in Spring Boot:

1. Custom Annotation for Logging

Define the Annotation

package com.example.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // Can be applied to methods
@Retention(RetentionPolicy.RUNTIME) // Available at runtime
public @interface LogExecutionTime {
}

Aspect for Handling the Annotation

package com.example.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogExecutionTimeAspect {

    @Around("@annotation(com.example.annotations.LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        
        Object proceed = joinPoint.proceed(); // Execute the method
        
        long executionTime = System.currentTimeMillis() - start;
        System.out.println(joinPoint.getSignature() + " executed in " + executionTime + "ms");
        
        return proceed;
    }
}

Use the Annotation

package com.example.service;

import com.example.annotations.LogExecutionTime;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    @LogExecutionTime
    public void performTask() throws InterruptedException {
        Thread.sleep(2000); // Simulate task
        System.out.println("Task performed");
    }
}

Output

When performTask() is called:

Task performed
public void com.example.service.MyService.performTask() executed in 2002ms

2. Custom Annotation for Validation

Define the Annotation

package com.example.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = NameValidator.class) // Link to the custom validator
@Target(ElementType.FIELD) // Can be applied to fields
@Retention(RetentionPolicy.RUNTIME) // Available at runtime
public @interface ValidName {
    String message() default "Invalid name"; // Default error message
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

Create the Validator

package com.example.validators;

import com.example.annotations.ValidName;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameValidator implements ConstraintValidator<ValidName, String> {

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        // Check if the name contains only letters and is not empty
        return name != null && name.matches("[A-Za-z]+");
    }
}

Use the Annotation

package com.example.dto;

import com.example.annotations.ValidName;

public class UserDTO {

    @ValidName
    private String name;

    // Getters and setters
}

Controller Example

package com.example.controller;

import com.example.dto.UserDTO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Validated
public class UserController {

    @PostMapping("/users")
    public String createUser(@RequestBody @Valid UserDTO userDTO) {
        return "User created: " + userDTO.getName();
    }
}

Behavior

	•	Input: {"name": "John123"}
	•	Output: 400 Bad Request: Invalid name

3. Custom Annotation for Role-Based Access

Define the Annotation

package com.example.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // Can be applied to methods
@Retention(RetentionPolicy.RUNTIME) // Available at runtime
public @interface Secured {
    String role(); // Role required to access the method
}

Aspect for Handling the Annotation

package com.example.aspects;

import com.example.annotations.Secured;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SecurityAspect {

    @Before("@annotation(com.example.annotations.Secured)")
    public void checkRole(MethodSignature methodSignature) {
        Secured secured = methodSignature.getMethod().getAnnotation(Secured.class);
        String requiredRole = secured.role();
        
        // Simulate getting the current user's role (e.g., from a security context)
        String currentUserRole = "USER";
        
        if (!currentUserRole.equals(requiredRole)) {
            throw new SecurityException("Access denied for role: " + currentUserRole);
        }
    }
}

Use the Annotation

package com.example.service;

import com.example.annotations.Secured;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Secured(role = "ADMIN")
    public void performAdminTask() {
        System.out.println("Performing admin task...");
    }
}

Output

	•	If the user’s role is not “ADMIN”, an exception is thrown:

Access denied for role: USER

	•	If the role matches, it prints:

Performing admin task...

4. Custom Annotation for Caching

Define the Annotation

package com.example.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Cacheable {
    String key();
}

Aspect for Handling Caching

package com.example.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class CachingAspect {

    private final Map<String, Object> cache = new HashMap<>();

    @Around("@annotation(com.example.annotations.Cacheable) && @annotation(cacheable)")
    public Object cacheResult(ProceedingJoinPoint joinPoint, com.example.annotations.Cacheable cacheable) throws Throwable {
        String key = cacheable.key();
        
        if (cache.containsKey(key)) {
            System.out.println("Returning cached value for key: " + key);
            return cache.get(key);
        }
        
        Object result = joinPoint.proceed(); // Execute the method
        cache.put(key, result);
        return result;
    }
}

Use the Annotation

package com.example.service;

import com.example.annotations.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class DataService {

    @Cacheable(key = "dataKey")
    public String fetchData() {
        System.out.println("Fetching data...");
        return "Data from DB";
    }
}

Output

First call to fetchData():

Fetching data...

Subsequent calls:

Returning cached value for key: dataKey

These examples demonstrate how custom annotations can encapsulate reusable logic in Spring Boot for various use cases like logging, validation, security, and caching.