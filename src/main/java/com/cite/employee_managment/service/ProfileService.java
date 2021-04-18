package com.cite.employee_managment.service;

import com.cite.employee_managment.dto.AttributeDto;
import com.cite.employee_managment.dto.Profile;

import java.util.List;

public interface ProfileService {

    Profile save(Profile profile);

    Profile getByEmpId(int empId);

    List<Profile> getByAttributes(List<AttributeDto> attributeDtos);
}
