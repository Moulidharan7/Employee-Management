package org.example.employee_management.CustomException;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(String message){
        super(message);
    }
}
