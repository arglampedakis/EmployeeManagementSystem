package com.cite.employee_managment.service;

import com.cite.employee_managment.dto.EmployeeDto;
import com.cite.employee_managment.model.Attribute;
import com.cite.employee_managment.model.Employee;

import java.util.List;

public interface EmployeeService {

    EmployeeDto save(EmployeeDto employeeDto);

    EmployeeDto findById(int empId);

    void delete(Employee employee);

    List<EmployeeDto> findAll();

    List<EmployeeDto> findByAttributes(Attribute... attributes);

}
