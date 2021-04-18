package com.cite.employee_managment.repo;

import com.cite.employee_managment.model.Attribute;
import com.cite.employee_managment.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Collection;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>, JpaSpecificationExecutor<Employee> {

    List<Employee> findByAttributesCollection(List<Attribute> attributes);
}