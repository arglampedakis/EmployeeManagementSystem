package com.cite.employee_managment.service;

import com.cite.employee_managment.dto.AttributeDto;
import com.cite.employee_managment.model.Attribute;

import java.util.List;

public interface AttributeService {

    AttributeDto save(AttributeDto attributeDto);

    void delete(AttributeDto attributeDto);

    AttributeDto findById(int attrId);

    List<AttributeDto> findAll();

    boolean checkIfAttrNameExists(String attrName);
}
