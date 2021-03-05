package com.cite.employee_managment.service;

import com.cite.employee_managment.dto.EmployeeDto;
import com.cite.employee_managment.mapper.EmployeeMapper;
import com.cite.employee_managment.model.Employee;
import com.cite.employee_managment.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public EmployeeDto findById(Integer empId) {
        Optional<Employee> employeeOptional = employeeRepository.findById(empId);

        if (employeeOptional.isPresent()) {
            return employeeMapper.employeeToEmployeeDto(employeeOptional.get());
        } else {
            throw new RuntimeException("Not Found");
        }
    }

    @Override
    public void delete(Employee employee) {
        employeeRepository.delete(employee);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }
}
