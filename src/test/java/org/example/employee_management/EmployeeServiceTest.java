package org.example.employee_management;

import org.example.employee_management.entity.Employee;
import org.example.employee_management.repository.EmployeeRepository;
import org.example.employee_management.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;


    @Test
    public void test_getEmployee(){
        Employee employee = new Employee();
        employee.setSalary(12000.0);
        when(employeeRepository.getById(1l)).thenReturn(employee);
        Employee result = employeeService.getEmployee(1l);
        assertEquals(12000.0,employee.getSalary());
    }

    @Test
    public void test_addEmployee(){
        Employee employee = new Employee();
        employee.setSalary(12000.0);
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
        Employee result = employeeService.addEmployee(employee);
        assertEquals(12000.0, employee.getSalary());
        verify(employeeRepository).save(employee);
    }

    @Test
    public void test_updateEmployee(){
        Employee employee = new Employee();
        employee.setSalary(10000.0);
        employee.setDepartment("EEE");
        employee.setName("stark");

        Long id = 1l;

        when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));
        Employee updated = new Employee();
        updated.setName("evans");
        updated.setDepartment("CS");
        updated.setSalary(15000.0);
        when(employeeRepository.save(any(Employee.class))).thenReturn(updated);

        Employee result = employeeService.updateEmployee(id,updated);
        assertEquals(15000.0,result.getSalary());
    }

    @Test
    public void test_getSalaryRange(){
        List<Employee> list = new ArrayList<>();
        Employee e1 = new Employee();
        e1.setSalary(12000.0);
        Employee e2 = new Employee();
        e2.setSalary(20000.0);
        list.add(e1);
        list.add(e2);

        when(employeeRepository.getSalaryRange(10000.0,15000.0)).thenReturn(list);
        List<Employee> result = employeeService.getSalaryRange(10000.0,15000.0);
        assertEquals(12000.0,result.get(0).getSalary());
        verify(employeeRepository).getSalaryRange(10000.0,15000.0);
    }
}
