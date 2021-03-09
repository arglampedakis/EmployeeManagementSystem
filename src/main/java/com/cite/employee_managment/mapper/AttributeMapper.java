package com.cite.employee_managment.mapper;

import com.cite.employee_managment.dto.AttributeDto;
import com.cite.employee_managment.model.Attribute;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface AttributeMapper {

    AttributeDto attributeToAttributeDto(Attribute attribute);

    Attribute attributeDtoToAttribute(AttributeDto attributeDto);

    List<AttributeDto> attributesToAttributeDtos(List<Attribute> attributes);

    List<Attribute> attributeDtosToAttributes(List<AttributeDto> attributeDtos);
}
