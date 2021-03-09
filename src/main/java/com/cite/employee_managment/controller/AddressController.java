package com.cite.employee_managment.controller;

import com.cite.employee_managment.dto.AddressDto;
import com.cite.employee_managment.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/address")
public class AddressController {

    private final AddressService addressService;

    @GetMapping(path = "/{addrId}", produces = {"application/json"})
    public ResponseEntity<AddressDto> getById(@PathVariable("addrId") int addrId) {
        return new ResponseEntity<>(addressService.findById(addrId), HttpStatus.OK);
    }

    @PostMapping(path = "/save", produces = {"application/json"})
    public ResponseEntity<AddressDto> save(@RequestBody AddressDto addressDto) {
        return new ResponseEntity<>(addressService.save(addressDto), HttpStatus.OK);
    }

    @GetMapping(path = "/delete/{addrId}", produces = {"application/json"})
    public void delete(@PathVariable("addrId") int addrId) {
        addressService.delete(
                addressService.findById(addrId));
    }
}
