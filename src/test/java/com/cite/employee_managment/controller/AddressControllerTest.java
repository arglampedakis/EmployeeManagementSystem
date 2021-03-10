package com.cite.employee_managment.controller;

import com.cite.employee_managment.dto.AddressDto;
import com.cite.employee_managment.service.AddressService;
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

import java.math.BigDecimal;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class AddressControllerTest {

    @Mock
    private AddressService addressService;

    @InjectMocks
    private AddressController addressController;

    private MockMvc mockMvc;
    private AddressDto validAddressDto;

    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        validAddressDto = AddressDto.builder()
                .addrId(1)
                .addrLatitude(BigDecimal.valueOf(23.733583))
                .addrLongitude(BigDecimal.valueOf(23.733583))
                .addrCountry("Greece")
                .addrCity("Athens")
                .addrStreetName("Koletti")
                .addrStreetNumber("5")
                .addrPostalCode("10681")
                .addrEmpId(1)
                .build();

        mockMvc = MockMvcBuilders.standaloneSetup(addressController).build();

        mapper = new ObjectMapper();
    }

    @AfterEach
    void tearDown() {
        reset(addressService);
    }

    @Test
    void getById() throws Exception {
        given(addressService.findById(anyInt())).willReturn(validAddressDto);

        mockMvc.perform(get("/api/v1/address/" + validAddressDto.getAddrId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.addrId", is(1)))
                .andExpect(jsonPath("$.addrCountry", is("Greece")));
    }

    @Test
    void save() throws Exception {
        given(addressService.save(any())).willReturn(validAddressDto);

        String json = mapper.writeValueAsString(validAddressDto);

        mockMvc.perform(
                post("/api/v1/address/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.addrId", is(1)))
                .andExpect(jsonPath("$.addrCountry", is("Greece")));
    }

    @Test
    void delete() {
    }
}