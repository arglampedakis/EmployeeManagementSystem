package com.cite.employee_managment.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "attribute")
@Data
public class Attribute implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ATTR_ID", nullable = false)
    private Integer attrId;

    @Column(name = "ATTR_Name", nullable = false)
    private String attrName;

    @Column(name = "ATTR_Value", nullable = false)
    private String attrValue;

    @ManyToMany(mappedBy = "attributesCollection")
    private Collection<Employee> employeeCollection;
}
