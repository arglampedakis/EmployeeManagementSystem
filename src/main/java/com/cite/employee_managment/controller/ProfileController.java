package com.cite.employee_managment.controller;

import com.cite.employee_managment.dto.AttributeDto;
import com.cite.employee_managment.dto.EmployeeDto;
import com.cite.employee_managment.dto.Profile;
import com.cite.employee_managment.mapper.AddressMapper;
import com.cite.employee_managment.mapper.AttributeMapper;
import com.cite.employee_managment.mapper.EmployeeMapper;
import com.cite.employee_managment.model.Employee;
import com.cite.employee_managment.service.AttributeService;
import com.cite.employee_managment.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/profile")
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.POST})
public class ProfileController {

    private final EmployeeService employeeService;
    private final AttributeService attributeService;
    private final AttributeMapper attributeMapper;
    private final EmployeeMapper employeeMapper;
    private final AddressMapper addressMapper;

    @GetMapping(path = "/{empId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Profile> getById(@PathVariable("empId") Integer empId) {

        Employee emp = employeeService.findEmployeeById(empId);
        List<AttributeDto> attributeDtos = attributeService.findAll();

        emp.getAttributesCollection().forEach( attr -> {
            attributeDtos.get(attributeDtos.indexOf(attr)).setChecked(true);
        });

        Profile profile = Profile.builder()
                .employeeDto(employeeMapper.employeeToEmployeeDto(emp))
                .addressDto(addressMapper.addressToAddressDto(emp.getAddress()))
                .attributeDtos(attributeDtos)
                .build();

        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    @PostMapping(path = "/save", produces = {"application/json"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> save(@RequestBody Profile profile) {
//        return new ResponseEntity<>(HttpStatus.OK);

        return null;
    }
}
