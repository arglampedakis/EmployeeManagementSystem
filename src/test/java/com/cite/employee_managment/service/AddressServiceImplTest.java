package com.cite.employee_managment.service;

import com.cite.employee_managment.dto.AddressDto;
import com.cite.employee_managment.mapper.AddressMapper;
import com.cite.employee_managment.model.Address;
import com.cite.employee_managment.model.Employee;
import com.cite.employee_managment.repo.AddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
    private AddressRepository addressRepository;

    @Mock
    private AddressMapper addressMapper;

    @InjectMocks
    private AddressServiceImpl addressService;

    private Address address;
    private AddressDto addressDto;

    @BeforeEach
    void setUp() {
        Employee employee = new Employee();
        employee.setEmpId(1);
        address = new Address();
        address.setAddrId(1);
        address.setAddrEmpid(employee);

        addressDto = new AddressDto();
        addressDto.setAddrId(1);
        addressDto.setAddrEmpId(1);

    }

    @Test
    void save() {
        given(addressRepository.save(address)).willReturn(address);
        given(addressMapper.addressToAddressDto(address)).willReturn(addressDto);
        given(addressMapper.addressDtoToAddress(addressDto)).willReturn(address);

        AddressDto returnedAddress = addressService.save(
                addressMapper.addressToAddressDto(address));

        then(addressRepository).should(times(1)).save(address);
        assertThat(returnedAddress).isNotNull();
        assertThat(returnedAddress).isEqualTo(addressMapper.addressToAddressDto(address));
    }

    @Test
    void delete() {
        given(addressMapper.addressToAddressDto(address)).willReturn(addressDto);
        given(addressMapper.addressDtoToAddress(addressDto)).willReturn(address);

        addressService.delete(
                addressMapper.addressToAddressDto(address));

        then(addressRepository).should(times(1)).delete(address);
    }

    @Test
    void findById() {
        Optional<Address> addressOptional = Optional.of(address);

        given(addressRepository.findById(1)).willReturn(addressOptional);
        given(addressMapper.addressToAddressDto(address)).willReturn(addressDto);

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