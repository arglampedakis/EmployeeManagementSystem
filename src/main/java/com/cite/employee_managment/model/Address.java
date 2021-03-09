package com.cite.employee_managment.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "address")
@Data
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ADDR_ID", nullable = false)
    private Integer addrId;

    @Column(name = "ADDR_Longitude", nullable = false)
    private BigDecimal addrLongitude;

    @Column(name = "ADDR_Latitude", nullable = false)
    private BigDecimal addrLatitude;

    @Column(name = "ADDR_Country", nullable = false)
    private String addrCountry;

    @Column(name = "ADDR_City", nullable = false)
    private String addrCity;

    @Column(name = "ADDR_Streetname", nullable = false)
    private String addrStreetName;

    @Column(name = "ADDR_Streetnumber", nullable = false)
    private String addrStreetNumber;

    @Column(name = "ADDR_Postalcode", nullable = false)
    private String addrPostalCode;

    @OneToOne
    @JoinColumn(name = "ADDR_EmpID", referencedColumnName = "EMP_ID")
    private Employee addrEmpid;

}
