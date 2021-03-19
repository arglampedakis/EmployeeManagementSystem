package com.cite.employee_managment.controller;

import com.cite.employee_managment.dto.AttributeDto;
import com.cite.employee_managment.service.AttributeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
class AttributeControllerTest {

    @Mock
    private AttributeService attributeService;

    @InjectMocks
    private AttributeController attributeController;

    private MockMvc mockMvc;
    private AttributeDto validAttributeDto;

    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        validAttributeDto = AttributeDto.builder()
                .attrId(1)
                .attrName("An Attribute")
                .attrValue("An Attribute Value")
                .build();

        mockMvc = MockMvcBuilders.standaloneSetup(attributeController).build();

        mapper = new ObjectMapper();
    }

    @AfterEach
    void tearDown() {
        reset(attributeService);
    }

    @Test
    void getById() throws Exception {
        given(attributeService.findById(anyInt())).willReturn(validAttributeDto);

        mockMvc.perform(get("/api/v1/attribute/" + validAttributeDto.getAttrId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.attrId", is(1)))
                .andExpect(jsonPath("$.attrName", is("An Attribute")));
    }

    @Test
    void getAll() throws Exception {
        List<AttributeDto> attributeDtos = getListOfAttributeDto();

        given(attributeService.findAll()).willReturn(attributeDtos);

        mockMvc.perform(
                get("/api/v1/attribute/")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].attrId", is(attributeDtos.get(0).getAttrId())))
                .andExpect(jsonPath("$[1].attrId", is(attributeDtos.get(1).getAttrId())));
    }

    private List<AttributeDto> getListOfAttributeDto() {
        List<AttributeDto> attributeDtos = new ArrayList<>();

        AttributeDto attributeDto1 = AttributeDto.builder()
                .attrId(1)
                .attrName("Attr 1")
                .attrValue("Val 1")
                .build();

        AttributeDto attributeDto2 = AttributeDto.builder()
                .attrId(2)
                .attrName("Attr 2")
                .attrValue("Val 2")
                .build();

        attributeDtos.add(attributeDto1);
        attributeDtos.add(attributeDto2);

        return attributeDtos;
    }

    @Test
    void save() throws Exception {
        given(attributeService.save(any())).willReturn(validAttributeDto);

        String json = mapper.writeValueAsString(validAttributeDto);

        mockMvc.perform(
                post("/api/v1/attribute/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", is(1)));
    }

    @Test
    void delete() {
    }


}