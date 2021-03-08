package com.cite.employee_managment.service;

import com.cite.employee_managment.dto.EmployeeDto;
import com.cite.employee_managment.mapper.EmployeeMapper;
import com.cite.employee_managment.mapper.EmployeeMapperImpl;
import com.cite.employee_managment.model.Attribute;
import com.cite.employee_managment.model.Employee;
import com.cite.employee_managment.repo.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    EmployeeRepository employeeRepository;

    @Spy
    EmployeeMapper employeeMapper = new EmployeeMapperImpl();

    @InjectMocks
    EmployeeServiceImpl employeeService;

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = new Employee();
    }

    @Test
    void save() {
        given(employeeRepository.save(employee)).willReturn(employee);

        EmployeeDto returnedEmployeeDto =
                employeeService.save(employeeMapper.employeeToEmployeeDto(employee));

        then(employeeRepository).should(times(1)).save(employee);
        assertThat(returnedEmployeeDto)
                .isEqualTo(employeeMapper.employeeToEmployeeDto(employee));
    }

    @Test
    void findById() {
        employee.setEmpId(1);
        Optional<Employee> employeeOptional = Optional.of(employee);

        given(employeeRepository.findById(1)).willReturn(employeeOptional);

        //when
        EmployeeDto employeeDto = employeeService.findById(1);

        then(employeeRepository).should(times(1)).findById(1);
        assertThat(employeeDto).isNotNull();
        assertThat(employeeDto.getEmpId()).isEqualTo(1);
    }

    @DisplayName("EmpID doesn't exist")
    @Test
    void findByIdNotExists() {
        Optional<Employee> employeeOptional = Optional.empty();

        given(employeeRepository.findById(anyInt())).willReturn(employeeOptional);

        assertThrows(RuntimeException.class, () -> {
            employeeService.findById(anyInt());
        });
    }

    @Test
    void delete() {
        //when
        employeeService.delete(employee);

        then(employeeRepository).should(times(1)).delete(employee);
    }

    @Test
    void findAll() {
        List<Employee> employees = createAndFill();

        given(employeeRepository.findAll()).willReturn(employees);

        //when
        List<EmployeeDto> employeeDtos = employeeService.findAll();

        then(employeeRepository).should(times(1)).findAll();
        assertThat(employeeDtos).hasSize(2);
        assertThat(employeeDtos).isEqualTo(employeeMapper.employeesToEmployeeDtos(employees));
        assertThat(employeeMapper.employeeDtosToEmployees(employeeDtos)).isEqualTo(employees);
    }

    @Test
    void findByAttributes() {
        Attribute attribute1 = new Attribute();
        Attribute attribute2 = new Attribute();

        List<Employee> employees = createAndFill();

        given(employeeRepository.findByAttributesCollection(attribute1, attribute2)).willReturn(employees);

        //when
        List<EmployeeDto> employeeDtos = employeeService.findByAttributes(attribute1, attribute2);

        then(employeeRepository).should(times(1)).findByAttributesCollection(attribute1, attribute2);
        assertThat(employeeDtos).hasSize(2);
        assertThat(employeeDtos).isEqualTo(employeeMapper.employeesToEmployeeDtos(employees));
        assertThat(employeeMapper.employeeDtosToEmployees(employeeDtos)).isEqualTo(employees);
    }

    private List<Employee> createAndFill() {
        List<Employee> employees = new ArrayList<>();
        Employee employee1 = new Employee();
        employee1.setEmpId(1);
        employees.add(employee1);
        Employee employee2 = new Employee();
        employee2.setEmpId(2);
        employees.add(employee2);
        return employees;
    }
}