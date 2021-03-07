package com.cite.employee_managment.model;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;

@Data
@Entity
@Table(name = "employee")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "EMP_ID", nullable = false)
    private Integer empId;

    @Basic(optional = false)
    @Column(name = "EMP_Name", nullable = false)
    private String empName;

    @Basic(optional = false)
    @Column(name = "EMP_DateOfBirth", nullable = false)
    private Timestamp empDateofbirth;

    @Basic(optional = false)
    @Column(name = "EMP_Vehicle")
    private boolean empVehicle;

    @Column(name = "EMP_Supervisor")
    private Integer empSupervisor;

    @OneToOne(cascade = CascadeType.REMOVE, mappedBy = "addrEmpid")
    private Address address;

    @JoinTable(name = "employeeattribute", joinColumns = {
            @JoinColumn(name = "EMPATTR_EmpID", referencedColumnName = "EMP_ID")}, inverseJoinColumns = {
            @JoinColumn(name = "EMPATTR_AttrID", referencedColumnName = "ATTR_ID")})
    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<Attribute> attributesCollection;

}
