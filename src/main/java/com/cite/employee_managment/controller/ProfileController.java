package com.cite.employee_managment.controller;

import com.cite.employee_managment.dto.AttributeDto;
import com.cite.employee_managment.dto.Profile;
import com.cite.employee_managment.service.ProfileService;
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

    private final ProfileService profileService;

    @GetMapping(path = "/{empId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Profile> getById(@PathVariable("empId") Integer empId) {
        return new ResponseEntity<>(profileService.getByEmpId(empId), HttpStatus.OK);
    }

    @PostMapping(path = "/save", produces = {"application/json"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> save(@RequestBody Profile profile) {
        return new ResponseEntity<>(profileService.save(profile).getEmployeeDto().getEmpId(), HttpStatus.OK);
    }

    @GetMapping(path = "/filteredByAttributes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Profile>> getByAttributes(@RequestParam List<AttributeDto> attributeDtos) {
        return new ResponseEntity<>(profileService.getByAttributes(attributeDtos), HttpStatus.OK);
    }
}
