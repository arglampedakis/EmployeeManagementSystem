package com.cite.employee_managment.service;

import com.cite.employee_managment.dto.EmployeeDto;
import com.cite.employee_managment.mapper.EmployeeMapper;
import com.cite.employee_managment.model.Attribute;
import com.cite.employee_managment.model.Employee;
import com.cite.employee_managment.repo.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    public EmployeeDto save(EmployeeDto employeeDto) {
        return employeeMapper.employeeToEmployeeDto(
                employeeRepository.save(
                        employeeMapper.employeeDtoToEmployee(employeeDto)));
    }

    @Override
    public EmployeeDto findById(int empId) {
        Optional<Employee> employeeOptional = employeeRepository.findById(empId);

        return employeeOptional.map(employeeMapper::employeeToEmployeeDto)
                .orElseThrow(() -> new RuntimeException("Not Found"));
    }

    @Override
    public Employee findEmployeeById(int empId) {
        Optional<Employee> employeeOptional = employeeRepository.findById(empId);

        if (employeeOptional.isPresent()) {
            return employeeOptional.get();
        } else {
            throw new RuntimeException("Not Found");
        }
    }

    @Override
    public void delete(EmployeeDto employeeDto) {
        employeeRepository.delete(
                employeeMapper.employeeDtoToEmployee(employeeDto));
    }

    @Override
    public List<EmployeeDto> findAll() {
        return employeeMapper.employeesToEmployeeDtos(
                employeeRepository.findAll());
    }

    //TODO fix this
    @Override
    public List<EmployeeDto> findByAttributes(List<Attribute> attributes) {
//        return employeeMapper.employeesToEmployeeDtos(
//                employeeRepository.findByAttributesCollection(attributes));
        return null;
    }


}
