package com.cite.employee_managment.controller;

import com.cite.employee_managment.dto.EmployeeDto;
import com.cite.employee_managment.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping(path = "/{empId}", produces = {"application/json"})
    public ResponseEntity<EmployeeDto> getById(@PathVariable("empId") Integer empId) {
        return new ResponseEntity<>(employeeService.findById(empId), HttpStatus.OK);
    }

    @GetMapping(path = "/", produces = {"application/json"})
    public ResponseEntity<List<EmployeeDto>> getAll() {
        return new ResponseEntity<>(employeeService.findAll(), HttpStatus.OK);
    }

    @PostMapping(path = "/save", produces = {"application/json"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeDto> save(@RequestBody EmployeeDto employeeDto) {
        return new ResponseEntity<>(employeeService.save(employeeDto), HttpStatus.OK);
    }

    @GetMapping(path = "/delete/{empId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable("empId") int empId) {
        employeeService.delete(
                employeeService.findById(empId));
    }

}