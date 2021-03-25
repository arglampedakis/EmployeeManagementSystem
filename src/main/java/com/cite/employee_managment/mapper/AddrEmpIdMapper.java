package com.cite.employee_managment.mapper;

import com.cite.employee_managment.model.Employee;
import com.cite.employee_managment.service.EmployeeService;
import org.springframework.stereotype.Component;

@Component
public class AddrEmpIdMapper {

    private final EmployeeService employeeService;

    public AddrEmpIdMapper(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    int asEmpId(Employee employee) {
        if (employee != null) {
            return employee.getEmpId();
        } else {
            return -1;
        }
    }

    Employee asEmployee(int empId) {
        if (empId != -1) {
            return employeeService.findEmployeeById(empId);
        } else {
            return null;
        }
    }
}
