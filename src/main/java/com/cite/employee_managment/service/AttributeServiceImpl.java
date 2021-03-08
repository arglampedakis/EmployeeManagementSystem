package com.cite.employee_managment.service;

import com.cite.employee_managment.dto.AttributeDto;
import com.cite.employee_managment.mapper.AttributeMapper;
import com.cite.employee_managment.model.Attribute;
import com.cite.employee_managment.repo.AttributeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttributeServiceImpl implements AttributeService {

    private final AttributeRepository attributeRepository;
    private final AttributeMapper attributeMapper;

    @Override
    public AttributeDto save(AttributeDto attributeDto) {
        return attributeMapper.attributeToAttributeDto(
                attributeRepository.save(
                        attributeMapper.attributeDtoToAttribute(attributeDto)));
    }

    @Override
    public void delete(Attribute attribute) {
        attributeRepository.delete(attribute);
    }

    @Override
    public AttributeDto findById(int attrId) {
        Optional<Attribute> attributeOptional = attributeRepository.findById(attrId);

        return attributeOptional.map(attributeMapper::attributeToAttributeDto)
                .orElseThrow(() -> new RuntimeException("Not Found"));
    }

    @Override
    public List<AttributeDto> findAll() {
        return attributeMapper
                .attributesToAttributeDtos(attributeRepository.findAll());
    }
}
