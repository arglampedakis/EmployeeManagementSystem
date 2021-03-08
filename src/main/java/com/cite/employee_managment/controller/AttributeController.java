package com.cite.employee_managment.controller;

import com.cite.employee_managment.dto.AttributeDto;
import com.cite.employee_managment.mapper.AttributeMapper;
import com.cite.employee_managment.service.AttributeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/attribute")
public class AttributeController {

    private final AttributeService attributeService;
    private final AttributeMapper attributeMapper;

    @GetMapping(path = "/{attrId}", produces = {"application/json"})
    public ResponseEntity<AttributeDto> getAttributeById(@PathVariable("attrId") Integer attrId) {
        return new ResponseEntity<>(attributeService.findById(attrId), HttpStatus.OK);
    }

    @GetMapping(path = "/", produces = {"application/json"})
    public ResponseEntity<List<AttributeDto>> getAll() {
        return new ResponseEntity<>(attributeService.findAll(), HttpStatus.OK);
    }

    @PostMapping(path = "/save", produces = {"application/json"})
    public ResponseEntity<AttributeDto> save(@RequestParam AttributeDto attributeDto) {
        return new ResponseEntity<>(attributeService.save(attributeDto), HttpStatus.OK);
    }

    @GetMapping(path = "/delete/{attrId}", produces = {"application/json"})
    public void delete(@PathVariable("attrId") int attrId) {
        attributeService.delete(
                attributeMapper.attributeDtoToAttribute(
                        attributeService.findById(attrId)));
    }

}
