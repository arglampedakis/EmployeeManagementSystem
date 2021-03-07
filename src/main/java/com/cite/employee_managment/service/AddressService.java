package com.cite.employee_managment.service;

import com.cite.employee_managment.dto.AddressDto;
import com.cite.employee_managment.model.Address;

public interface AddressService {

    AddressDto save(Address address);

    void delete(Address address);

    AddressDto findById(int addrId);
}
