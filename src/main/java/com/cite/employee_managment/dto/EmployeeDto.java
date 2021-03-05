package com.cite.employee_managment.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class EmployeeDto {

    @Builder
    public EmployeeDto(Integer empId, String empName, OffsetDateTime empDateOfBirth, boolean empVehicle, Integer empSupervisor) {
        this.empId = empId;
        this.empName = empName;
        this.empDateOfBirth = empDateOfBirth;
        this.empVehicle = empVehicle;
        this.empSupervisor = empSupervisor;
    }

    @JsonProperty("empId")
    private Integer empId;

    @JsonProperty("empName")
    private String empName;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ssZ", shape=JsonFormat.Shape.STRING)
    @JsonProperty("empDateOfBirth")
    private OffsetDateTime empDateOfBirth;

    @JsonProperty("empVehicle")
    private boolean empVehicle;

    @JsonProperty("empSupervisor")
    private Integer empSupervisor;
}
