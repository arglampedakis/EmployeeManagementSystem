package com.cite.employee_managment.service;

import com.cite.employee_managment.dto.AddressDto;
import com.cite.employee_managment.mapper.AddressMapper;
import com.cite.employee_managment.mapper.AddressMapperImpl;
import com.cite.employee_managment.model.Address;
import com.cite.employee_managment.repo.AddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class AddressServiceImplTest {

    @Mock
    AddressRepository addressRepository;

    @Spy
    AddressMapper addressMapper = new AddressMapperImpl();

    @InjectMocks
    AddressServiceImpl addressService;

    private Address address;

    @BeforeEach
    void setUp() {
        address = new Address();
    }

    @Test
    void save() {
        Address address = new Address();
        given(addressRepository.save(address)).willReturn(address);

        AddressDto returnedAddress = addressService.save(
                addressMapper.addressToAddressDto(address));

        then(addressRepository).should(times(1)).save(address);
        assertThat(returnedAddress).isNotNull();
        assertThat(returnedAddress).isEqualTo(addressMapper.addressToAddressDto(address));
        assertThat(addressMapper.addressDtoToAddress(returnedAddress)).isEqualTo(address);
    }

    @Test
    void delete() {
        addressService.delete(
                addressMapper.addressToAddressDto(address));

        then(addressRepository).should(times(1)).delete(address);
    }

    @Test
    void findById() {
        address.setAddrId(1);
        Optional<Address> addressOptional = Optional.of(address);

        given(addressRepository.findById(1)).willReturn(addressOptional);

        //when
        AddressDto addressDto = addressService.findById(1);

        then(addressRepository).should(times(1)).findById(1);
        assertThat(addressDto).isNotNull();
        assertThat(addressDto.getAddrId()).isEqualTo(1);
        assertThat(addressDto).isEqualTo(addressMapper.addressToAddressDto(address));
    }

    @Test
    void findByIdNotFound() {
        Optional<Address> addressOptional = Optional.empty();

        given(addressRepository.findById(anyInt())).willReturn(addressOptional);

        assertThrows(RuntimeException.class, () -> {
            addressService.findById(anyInt());
        });
    }
}