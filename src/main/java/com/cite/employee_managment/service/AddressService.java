package com.cite.employee_managment.service;

import com.cite.employee_managment.dto.AddressDto;
import com.cite.employee_managment.model.Address;

public interface AddressService {

    AddressDto save(AddressDto addressDto);

    void delete(AddressDto addressDto);

    AddressDto findById(int addrId);
}
