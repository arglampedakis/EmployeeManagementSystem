package com.cite.employee_managment.service;

import com.cite.employee_managment.model.Attribute;
import com.cite.employee_managment.repo.AttributeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class AttributeServiceImplTest {

    @Mock
    AttributeRepository attributeRepository;

    @InjectMocks
    AttributeServiceImpl attributeService;

    @Test
    void save() {
        //given
        Attribute attribute = new Attribute();
        given(attributeRepository.save(attribute)).willReturn(attribute);
        //when
        Attribute returnedAttribute = attributeService.save(attribute);
        //then
        then(attributeRepository).should().save(attribute);
        assertThat(returnedAttribute).isNotNull();
    }
}