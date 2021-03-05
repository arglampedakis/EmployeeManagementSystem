package com.cite.employee_managment.service;

import com.cite.employee_managment.dto.EmployeeDto;
import com.cite.employee_managment.model.Employee;
import com.cite.employee_managment.repo.EmployeeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    EmployeeRepository employeeRepository;

    @InjectMocks
    EmployeeServiceImpl employeeService;

    @Test
    void save() {
        Employee employee = new Employee();
        given(employeeRepository.save(employee)).willReturn(employee);

        Employee returnedEmployee = employeeService.save(employee);

        then(employeeRepository).should().save(employee);
        assertThat(returnedEmployee).isNotNull();
    }

    @Test
    void findById() {
        Employee employee = new Employee();
        employee.setEmpId(1);
        Optional<Employee> employeeOptional = Optional.of(employee);

        given(employeeRepository.findById(anyInt())).willReturn(employeeOptional);

        EmployeeDto employeeDto = employeeService.findById(1);

        then(employeeRepository).should().findById(anyInt());
        assertThat(employeeDto).isNotNull();
        assertThat(employeeDto.getEmpId()).isEqualTo(1);
    }

    @DisplayName("EmpID doesn't exist")
    @Test
    void findByIdNotExists() {
        Employee employee = new Employee();
        Optional<Employee> employeeOptional = Optional.empty();

        given(employeeRepository.findById(anyInt())).willReturn(employeeOptional);

        assertThrows(RuntimeException.class, () -> {
            employeeService.findById(anyInt());
        });
    }
}