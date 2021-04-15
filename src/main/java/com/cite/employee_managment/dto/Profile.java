package com.cite.employee_managment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Profile {

    private EmployeeDto employeeDto;
    private AddressDto addressDto;
    private List<AttributeDto> attributeDtos;
}
