package com.cite.employee_managment.service;

import com.cite.employee_managment.dto.AttributeDto;
import com.cite.employee_managment.dto.Profile;
import com.cite.employee_managment.mapper.AttributeMapper;
import com.cite.employee_managment.mapper.ProfileMapper;
import com.cite.employee_managment.repo.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProfileServiceImpl implements ProfileService {

    private final EmployeeService employeeService;
    private final AttributeMapper attributeMapper;
    private final EmployeeRepository employeeRepository;
    private final ProfileMapper profileMapper;

    @Override
    public Profile save(Profile profile) {
        return profileMapper.asProfile(
                employeeRepository.save(
                        profileMapper.asEmployee(profile)));
    }

    @Override
    public Profile getByEmpId(int empId) {
        return profileMapper.asProfile(
                employeeService.findEmployeeById(empId));
    }

    @Override
    public List<Profile> getByAttributes(List<AttributeDto> attributeDtos) {
        return profileMapper.asProfileList(
                employeeRepository.findByAttributesCollection(
                        attributeMapper.attributeDtosToAttributes(attributeDtos)));
    }
}
