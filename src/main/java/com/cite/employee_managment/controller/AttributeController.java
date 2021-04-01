package com.cite.employee_managment.controller;

import com.cite.employee_managment.dto.AttributeDto;
import com.cite.employee_managment.service.AttributeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/attribute")
@CrossOrigin(origins = "http://localhost:4200")
public class AttributeController {

    private final AttributeService attributeService;

    @GetMapping(path = "/{attrId}", produces = {"application/json"})
    public ResponseEntity<AttributeDto> getById(@PathVariable("attrId") Integer attrId) {
        return new ResponseEntity<>(attributeService.findById(attrId), HttpStatus.OK);
    }

    @GetMapping(path = "/", produces = {"application/json"})
    public ResponseEntity<List<AttributeDto>> getAll() {
        return new ResponseEntity<>(attributeService.findAll(), HttpStatus.OK);
    }

    @PostMapping(path = "/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> save(@RequestBody AttributeDto attributeDto) {
        return new ResponseEntity<>(attributeService.save(attributeDto).getAttrId(), HttpStatus.OK);
    }

    @GetMapping(path = "/delete/{attrId}", produces = {"application/json"})
    public void delete(@PathVariable("attrId") int attrId) {
        //TODO change it to DeleteMapping
        attributeService.delete(
                attributeService.findById(attrId));
    }

    @GetMapping(path = "/checkName/{attrName}")
    public ResponseEntity<Boolean> checkAttrName(@PathVariable String attrName) {
        return new ResponseEntity<>(
                attributeService.checkIfAttrNameExists(attrName),
                HttpStatus.OK);
    }

}
