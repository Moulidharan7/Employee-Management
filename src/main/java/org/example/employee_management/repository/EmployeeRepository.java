package org.example.employee_management.repository;

import org.example.employee_management.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(value = "select * from employee order by salary desc limit 1",nativeQuery = true)
    Employee getMaxSalary();

    @Query(value = "select * from Employee where salary between :min and :max",nativeQuery = true)
    List<Employee> getSalaryRange(double min, double max);

    List<Employee> findBySalaryBetween(double min,double max);

    Employee findTopByOrderBySalaryDesc();
}
