package com.cite.employee_managment.mapper;

import com.cite.employee_managment.dto.EmployeeDto;
import com.cite.employee_managment.model.Employee;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    EmployeeDto employeeToEmployeeDto(Employee employee);

    Employee employeeDtoToEmployee(EmployeeDto employeeDTO);

    List<EmployeeDto> employeesToEmployeeDtos(List<Employee> employees);

    List<Employee> employeeDtosToEmployees(List<EmployeeDto> employeeDtos);
}
