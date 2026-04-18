package org.example.employee_management.controller;

import org.example.employee_management.entity.Employee;
import org.example.employee_management.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {


    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getEmployees(){
        return employeeService.getAllEmployees();
    }

    @PostMapping("/create")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
        Employee emp = employeeService.addEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(emp);
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable Long id){
        return employeeService.getEmployee(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,@RequestBody Employee updated){
        Employee emp = employeeService.updateEmployee(id,updated);
        return ResponseEntity.status(HttpStatus.CREATED).body(emp);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable Long id){
        employeeService.delete(id);
    }

    @GetMapping("/salary")
    public List<Employee> getSalaryRange(@RequestParam double min, @RequestParam double max){
        return employeeService.getSalaryRange(min,max);
    }

    @GetMapping("/salary/new")
    public List<Employee> getSalaryRange1(@RequestParam double min, @RequestParam double max){
        return employeeService.getSalaryRange1(min,max);
    }

    @GetMapping("/max-salary")
    public Employee getMaxSalary(){
        return employeeService.getMaxSalary();
    }

    @GetMapping("/max-salary/new")
    public Employee getMaxSalary1(){
        return employeeService.getMaxSalary1();
    }

    @GetMapping("/minSalary")
    public Employee getMinSalary(){
        return employeeService.getMinSalary();
    }
}
