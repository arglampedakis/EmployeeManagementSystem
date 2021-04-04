package com.cite.employee_managment.controller;

import com.cite.employee_managment.dto.AttributeDto;
import com.cite.employee_managment.dto.EmployeeDto;
import com.cite.employee_managment.mapper.AttributeMapper;
import com.cite.employee_managment.model.Attribute;
import com.cite.employee_managment.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/employee")
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.POST})
public class EmployeeController {

    private final EmployeeService employeeService;
    private final AttributeMapper attributeMapper;

    @GetMapping(path = "/{empId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeDto> getById(@PathVariable("empId") Integer empId) {
        return new ResponseEntity<>(employeeService.findById(empId), HttpStatus.OK);
    }

    @GetMapping(path = "/attributes/{empId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AttributeDto>> getEmployeeAttributesByEmpId(@PathVariable("empId") Integer empId) {
        return new ResponseEntity<>(
                attributeMapper.attributesToAttributeDtos(
                        (List<Attribute>) employeeService.findEmployeeById(empId).getAttributesCollection())
                , HttpStatus.OK);
    }

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EmployeeDto>> getAll() {
        return new ResponseEntity<>(employeeService.findAll(), HttpStatus.OK);
    }

    @PostMapping(path = "/save", produces = {"application/json"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> save(@RequestBody EmployeeDto employeeDto) {
        return new ResponseEntity<>(employeeService.save(employeeDto).getEmpId(), HttpStatus.OK);
    }

    //TODO change the method to POST or DELETE
    @GetMapping(path = "/delete/{empId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable("empId") int empId) {
        employeeService.delete(
                employeeService.findById(empId));
    }

}