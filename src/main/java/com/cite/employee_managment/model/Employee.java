package com.cite.employee_managment.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

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
    @Column(name = "EMP_dateofbirth", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date empDateOfBirth;

    @Basic(optional = false)
    @Column(name = "EMP_Vehicle")
    private boolean empVehicle;

    @Column(name = "EMP_Supervisor")
    private Integer empSupervisor;

//    @JoinColumn(name = "EMP_Supervisor", referencedColumnName = "EMP_ID")
//    @ManyToOne
//    private Employee empSupervisor;

    @OneToOne(cascade = CascadeType.REMOVE, mappedBy = "addrEmpid")
    private Address address;

    @JoinTable(name = "employeeattribute", joinColumns = {
            @JoinColumn(name = "EMPATTR_EmpID", referencedColumnName = "EMP_ID")}, inverseJoinColumns = {
            @JoinColumn(name = "EMPATTR_AttrID", referencedColumnName = "ATTR_ID")})
    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<Attribute> attributesCollection;

}
