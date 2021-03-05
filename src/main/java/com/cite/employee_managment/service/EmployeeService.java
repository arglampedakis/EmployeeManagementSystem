package com.cite.employee_managment.service;

import com.cite.employee_managment.dto.EmployeeDto;
import com.cite.employee_managment.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee save(Employee employee);

    EmployeeDto findById(Integer empId);

    void delete(Employee employee);

    List<Employee> findAll();

}
