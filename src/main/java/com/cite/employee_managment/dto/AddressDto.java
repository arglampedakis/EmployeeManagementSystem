package com.cite.employee_managment.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AddressDto {
    private Integer addrId;
    private BigDecimal addrLongitude;
    private BigDecimal addrLatitude;
    private String addrCountry;
    private String addrStreetName;
    private String addrCity;
    private String addrStreetNumber;
    private String addrPostalCode;
    private int addrEmpId;

}
