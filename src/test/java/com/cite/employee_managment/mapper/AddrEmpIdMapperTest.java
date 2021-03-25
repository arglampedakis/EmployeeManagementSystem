package com.cite.employee_managment.mapper;

import com.cite.employee_managment.model.Employee;
import com.cite.employee_managment.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class AddrEmpIdMapperTest {

    @Mock
    EmployeeService employeeService;

    @InjectMocks
    AddrEmpIdMapper addrEmpIdMapper;

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = new Employee();
        employee.setEmpId(1);
    }

    @Test
    void asEmpId() {
        int empId = addrEmpIdMapper.asEmpId(employee);

        assertThat(empId).isEqualTo(1);
    }

    @Test
    void asEmployee() {
        given(employeeService.findEmployeeById(anyInt())).willReturn(employee);

        Employee returnedEmployee = addrEmpIdMapper.asEmployee(1);

        assertThat(returnedEmployee).isEqualTo(employee);
    }
}