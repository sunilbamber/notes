Here are additional examples of using AOP in Spring Boot to handle various cross-cutting concerns like logging, security, and exception handling:

1. Logging Input and Output of Methods

This example logs the input parameters and output of a method.

Aspect Class:

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Around("execution(* com.example.service.*.*(..))")
    public Object logMethodInputAndOutput(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Method called: " + joinPoint.getSignature());
        System.out.println("Input arguments: " + java.util.Arrays.toString(joinPoint.getArgs()));
        
        Object result = joinPoint.proceed(); // Proceed to the target method
        
        System.out.println("Method result: " + result);
        return result;
    }
}

Service Class:

package com.example.service;

import org.springframework.stereotype.Service;

@Service
public class MyService {
    public String sayHello(String name) {
        return "Hello, " + name + "!";
    }
}

Output:

When you call sayHello("John"), the console logs:

Method called: String com.example.service.MyService.sayHello(String)
Input arguments: [John]
Method result: Hello, John!

2. Timing the Execution of Methods

This example measures the execution time of methods.

Aspect Class:

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExecutionTimeAspect {

    @Around("execution(* com.example.service.*.*(..))")
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        
        Object result = joinPoint.proceed(); // Proceed with the method execution
        
        long executionTime = System.currentTimeMillis() - start;
        System.out.println(joinPoint.getSignature() + " executed in " + executionTime + "ms");
        
        return result;
    }
}

Output:

When you invoke a method, you’ll see:

public String com.example.service.MyService.sayHello(String) executed in 5ms

3. Securing Methods Based on Roles

This example checks a user’s role before allowing the method to execute.

Custom Annotation:

package com.example.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Secured {
    String role();
}

Aspect Class:

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SecurityAspect {

    @Before("@annotation(com.example.security.Secured)")
    public void checkRole(MethodSignature methodSignature) {
        Secured secured = methodSignature.getMethod().getAnnotation(Secured.class);
        String requiredRole = secured.role();
        
        // Simulate getting the user's role (in a real app, fetch from security context)
        String currentUserRole = "USER"; 
        
        if (!currentUserRole.equals(requiredRole)) {
            throw new SecurityException("Access denied for role: " + currentUserRole);
        }
    }
}

Service Class:

package com.example.service;

import com.example.security.Secured;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Secured(role = "ADMIN")
    public void performAdminTask() {
        System.out.println("Performing admin task...");
    }
}

Output:

	•	If the user role is not “ADMIN”, an exception is thrown:

Access denied for role: USER

	•	If the role matches, it prints:

Performing admin task...

4. Exception Handling

This example demonstrates a global exception handler using AOP.

Aspect Class:

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.AfterThrowing;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionHandlingAspect {

    @AfterThrowing(pointcut = "execution(* com.example.service.*.*(..))", throwing = "exception")
    public void handleException(Exception exception) {
        System.out.println("Exception caught in AOP: " + exception.getMessage());
    }
}

Service Class:

package com.example.service;

import org.springframework.stereotype.Service;

@Service
public class ErrorProneService {

    public void riskyMethod() {
        throw new RuntimeException("Something went wrong!");
    }
}

Output:

When riskyMethod() is called:

Exception caught in AOP: Something went wrong!

5. Dynamic Data Masking

Mask sensitive data (e.g., user passwords) using AOP.

Aspect Class:

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataMaskingAspect {

    @Around("execution(* com.example.service.UserService.getUserDetails(..))")
    public Object maskSensitiveData(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();
        if (result instanceof String) {
            return ((String) result).replaceAll("(?<=.{3}).(?=.*@)", "*");
        }
        return result;
    }
}

Service Class:

package com.example.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    public String getUserDetails() {
        return "john.doe@example.com"; // Example sensitive data
    }
}

Output:

When getUserDetails() is called, the console logs:

Result: joh*******@example.com

These examples demonstrate how you can use AOP for different scenarios in Spring Boot applications. Feel free to adapt these templates for your specific use cases!