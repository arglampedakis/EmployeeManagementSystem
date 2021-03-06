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
    public void delete(AttributeDto attributeDto) {
        attributeRepository.delete(
                attributeMapper.attributeDtoToAttribute(attributeDto));
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

    //TODO create test
    @Override
    public boolean checkIfAttrNameExists(String attrName) {
        Attribute attribute = new Attribute();
        attribute = attributeRepository.findAttributeByAttrName(attrName);
        return attribute == null;
    }
}
