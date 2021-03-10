package com.cite.employee_managment.controller;

import com.cite.employee_managment.dto.EmployeeDto;
import com.cite.employee_managment.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    private MockMvc mockMvc;
    private EmployeeDto validEmployeeDto;

    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        validEmployeeDto = EmployeeDto.builder()
                .empId(1)
                .empName("John Doe")
                .empDateOfBirth(LocalDateTime.now())
                .empVehicle(false)
                .empSupervisor(2)
                .build();

        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();

        mapper = new ObjectMapper();
    }

    @AfterEach
    void tearDown() {
        reset(employeeService);
    }

    @Test
    void getById() throws Exception {
        given(employeeService.findById(anyInt())).willReturn(validEmployeeDto);

        mockMvc.perform(get("/api/v1/employee/" + validEmployeeDto.getEmpId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.empId", is(1)))
                .andExpect(jsonPath("$.empName", is("John Doe")));
    }

    @Disabled("both the controller and the test need refactoring")
    @Test
    void getByIdNotFound() throws Exception {
        given(employeeService.findById(anyInt())).willThrow(RuntimeException.class);

        mockMvc.perform(get("/api/v1/employee/" + validEmployeeDto.getEmpId()))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getAll() throws Exception {
        List<EmployeeDto> employeeDtoList = getListOfEmployeeDto();

        given(employeeService.findAll()).willReturn(employeeDtoList);

        mockMvc.perform(
                get("/api/v1/employee/")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].empId", is(employeeDtoList.get(0).getEmpId())))
                .andExpect(jsonPath("$[1].empId", is(employeeDtoList.get(1).getEmpId())));
    }

    private List<EmployeeDto> getListOfEmployeeDto() {
        List<EmployeeDto> employeeDtoList = new ArrayList<>();

        EmployeeDto employeeDto1 = EmployeeDto.builder()
                .empId(1)
                .empName("John Doe")
                .empDateOfBirth(LocalDateTime.now())
                .empVehicle(false)
                .empSupervisor(2)
                .build();

        EmployeeDto employeeDto2 = EmployeeDto.builder()
                .empId(2)
                .empName("Mark Johnson")
                .empDateOfBirth(LocalDateTime.now())
                .empVehicle(true)
                .empSupervisor(1)
                .build();

        employeeDtoList.add(employeeDto1);
        employeeDtoList.add(employeeDto2);

        return employeeDtoList;
    }

    @Test
    void save() throws Exception {
        given(employeeService.save(any())).willReturn(validEmployeeDto);

        String json = mapper.writeValueAsString(validEmployeeDto);

        mockMvc.perform(
                post("/api/v1/employee/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.empId", is(1)))
                .andExpect(jsonPath("$.empName", is("John Doe")));
    }

    @Test
    void delete() {
    }
}