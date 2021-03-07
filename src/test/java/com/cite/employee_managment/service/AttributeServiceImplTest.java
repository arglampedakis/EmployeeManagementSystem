package com.cite.employee_managment.service;

import com.cite.employee_managment.dto.AttributeDto;
import com.cite.employee_managment.mapper.AttributeMapper;
import com.cite.employee_managment.mapper.AttributeMapperImpl;
import com.cite.employee_managment.model.Attribute;
import com.cite.employee_managment.repo.AttributeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class AttributeServiceImplTest {

    @Mock
    AttributeRepository attributeRepository;

    @Spy
    AttributeMapper attributeMapper = new AttributeMapperImpl();

    @InjectMocks
    AttributeServiceImpl attributeService;

    private Attribute attribute;

    @BeforeEach
    void setUp() {
        attribute = new Attribute();
    }

    @Test
    void save() {
        //given
        given(attributeRepository.save(attribute)).willReturn(attribute);
        //when
        AttributeDto returnedAttributeDto = attributeService.save(attribute);
        //then
        then(attributeRepository).should(times(1)).save(attribute);
        assertThat(returnedAttributeDto).isNotNull();
        assertThat(returnedAttributeDto).isEqualTo(attributeMapper.attributeToAttributeDto(attribute));
    }

    @Test
    void delete() {
        attributeService.delete(attribute);

        then(attributeRepository).should().delete(attribute);
    }

    @Test
    void findById() {
        attribute.setAttrId(1);
        Optional<Attribute> attributeOptional = Optional.of(attribute);
        given(attributeRepository.findById(1)).willReturn(attributeOptional);

        AttributeDto attributeDto = attributeService.findById(1);

        then(attributeRepository).should(times(1)).findById(1);
        assertThat(attributeDto).isNotNull();
        assertThat(attributeDto.getAttrId()).isEqualTo(1);
    }

    @Test
    void findAll() {
        List<Attribute> attributes = createAndFill();

        given(attributeRepository.findAll()).willReturn(attributes);

        List<AttributeDto> attributeDtos = attributeService.findAll();

        then(attributeRepository).should(times(1)).findAll();
        assertThat(attributeDtos).hasSize(2);
        assertThat(attributeMapper.attributesToAttributeDtos(attributes)).isEqualTo(attributeDtos);
        assertThat(attributes).isEqualTo(attributeMapper.attributeDtosToAttributes(attributeDtos));
    }

    private List<Attribute> createAndFill() {
        List<Attribute> attributes = new ArrayList<>();
        Attribute attribute1 = new Attribute();
        Attribute attribute2 = new Attribute();
        attribute1.setAttrId(1);
        attribute2.setAttrId(2);
        attributes.add(attribute1);
        attributes.add(attribute2);
        return attributes;

    }
}