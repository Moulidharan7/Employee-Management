package org.example.employee_management.service;

import org.example.employee_management.CustomException.EmployeeNotFoundException;
import org.example.employee_management.entity.Employee;
import org.example.employee_management.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public Employee getEmployee(Long id){
        return employeeRepository.getById(id);
    }

    public Employee addEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee updated){
        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));

        existing.setDepartment(updated.getDepartment());
        existing.setName(updated.getName());
        existing.setSalary(updated.getSalary());
        return employeeRepository.save(existing);
    }

    public void delete(Long id){
        Employee employee = employeeRepository.getById(id);
        employeeRepository.delete(employee);
    }

    public List<Employee> getSalaryRange(double min, double max){
        return employeeRepository.getSalaryRange(min,max);
    }

    public Employee getMaxSalary(){
        return employeeRepository.getMaxSalary();
    }

    public List<Employee> getSalaryRange1(double min, double max){
        return employeeRepository.findBySalaryBetween(min,max);
    }

    public Employee getMaxSalary1(){
        return employeeRepository.findTopByOrderBySalaryDesc();
    }

    public Employee getMinSalary(){
        return employeeRepository.findTopByOrderBySalaryAsc();
    }

}
