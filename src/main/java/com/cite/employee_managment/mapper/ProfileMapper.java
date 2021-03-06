package com.cite.employee_managment.mapper;

import com.cite.employee_managment.dto.AttributeDto;
import com.cite.employee_managment.dto.Profile;
import com.cite.employee_managment.model.Employee;
import com.cite.employee_managment.service.AttributeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class ProfileMapper {

    private final EmployeeMapper employeeMapper;
    private final AddressMapper addressMapper;
    private final AttributeMapper attributeMapper;
    private final AttributeService attributeService;

    public Employee asEmployee(Profile profile) {
        Employee emp = employeeMapper.employeeDtoToEmployee(profile.getEmployeeDto());
        emp.setAddress(addressMapper.addressDtoToAddress(profile.getAddressDto()));
        emp.setAttributesCollection(attributeMapper
                .attributeDtosToAttributes(
                        profile.getAttributeDtos().stream()
                                .filter(AttributeDto::isSelected)
                                .collect(Collectors.toList())));
        return emp;
    }

    public Profile asProfile(Employee employee) {
        List<AttributeDto> attributeDtos = attributeService.findAll();
        employee.getAttributesCollection().forEach(attr -> {
                    attributeDtos.stream()
                            .filter(attrDto -> attrDto.getAttrId().equals(attr.getAttrId()))
                            .findFirst().get().setSelected(true);
                }
        );

        return Profile.builder()
                .employeeDto(employeeMapper.employeeToEmployeeDto(employee))
                .addressDto(addressMapper.addressToAddressDto(employee.getAddress()))
                .attributeDtos(attributeDtos)
                .build();
    }

    public List<Profile> asProfileList(List<Employee> employees) {
        return employees.stream()
                .map(this::asProfile)
                .collect(Collectors.toList());
    }

    public List<Employee> asEmployeeList(List<Profile> profiles) {
        return profiles.stream()
                .map(this::asEmployee)
                .collect(Collectors.toList());
    }
}
