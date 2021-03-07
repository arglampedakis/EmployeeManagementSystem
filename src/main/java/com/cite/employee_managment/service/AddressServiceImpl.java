package com.cite.employee_managment.service;

import com.cite.employee_managment.dto.AddressDto;
import com.cite.employee_managment.mapper.AddressMapper;
import com.cite.employee_managment.model.Address;
import com.cite.employee_managment.repo.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    @Override
    public AddressDto save(Address address) {
        return addressMapper
                .addressToAddressDto(addressRepository.save(address));
    }

    @Override
    public void delete(Address address) {
        addressRepository.delete(address);
    }

    @Override
    public AddressDto findById(int addrId) {
        Optional<Address> addressOptional = addressRepository.findById(addrId);

        return addressOptional
                .map(addressMapper::addressToAddressDto)
                .orElseThrow(() -> new RuntimeException("Not found"));
    }
}
