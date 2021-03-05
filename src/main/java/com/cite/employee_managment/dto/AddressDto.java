package com.cite.employee_managment.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AddressDto {
    private Integer addrId;
    private BigDecimal addrLongitude;
    private BigDecimal addrLatitude;
    private String addrCountry;
    private String addrStreetName;
    private String addrCity;
    private String addrStreetNumber;
    private String addrPostalCode;
    private Integer addrEmpId;

}
