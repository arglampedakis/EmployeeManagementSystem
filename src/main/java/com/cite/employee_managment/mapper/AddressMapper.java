package com.cite.employee_managment.mapper;

import com.cite.employee_managment.dto.AddressDto;
import com.cite.employee_managment.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(uses = AddrEmpIdMapper.class)
public interface AddressMapper {

    @Mapping(source = "addrEmpid", target = "addrEmpId")
    AddressDto addressToAddressDto(Address address);

    @Mapping(source = "addrEmpId", target = "addrEmpid")
    Address addressDtoToAddress(AddressDto addressDto);

    List<AddressDto> addressesToAddressDtos(List<Address> addresses);

    List<Address> addressDtosToAddresses(List<AddressDto> addressDtos);
}
