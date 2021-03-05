package com.cite.employee_managment.service;

import com.cite.employee_managment.model.Address;
import com.cite.employee_managment.repo.AddressRepository;
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
class AddressServiceImplTest {

    @Mock
    AddressRepository addressRepository;

    @InjectMocks
    AddressServiceImpl addressService;

    @Test
    void save() {
        Address address = new Address();
        given(addressRepository.save(address)).willReturn(address);

        Address returnedAddress = addressService.save(address);

        then(addressRepository).should().save(address);
        assertThat(returnedAddress).isNotNull();
    }
}