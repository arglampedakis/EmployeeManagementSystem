package com.cite.employee_managment.mapper;

import com.cite.employee_managment.dto.EmployeeDto;
import com.cite.employee_managment.model.Employee;
import org.mapstruct.Mapper;

@Mapper(uses = DateMapper.class)
public interface EmployeeMapper {

    EmployeeDto employeeToEmployeeDto(Employee employee);

    Employee employeeDtoToEmployee(EmployeeDto employeeDTO);
}
