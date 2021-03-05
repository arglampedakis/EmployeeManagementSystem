package com.cite.employee_managment.repo;

import com.cite.employee_managment.model.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AttributeRepository extends JpaRepository<Attribute, Integer>, JpaSpecificationExecutor<Attribute> {

}