package com.cite.employee_managment.mapper;

import com.cite.employee_managment.dto.AddressDto;
import com.cite.employee_managment.model.Address;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface AddressMapper {

    AddressDto addressToAddressDto(Address address);

    Address addressDtoToAddress(AddressDto addressDto);

    List<AddressDto> addressesToAddressDtos(List<Address> addresses);

    List<Address> addressDtosToAddresses(List<AddressDto> addressDtos);
}
