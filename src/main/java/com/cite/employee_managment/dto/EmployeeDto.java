package com.cite.employee_managment.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@AllArgsConstructor
public class EmployeeDto {

    //    @JsonProperty("empId")
    private Integer empId;

    //    @JsonProperty("empName")
    private String empName;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ", shape = JsonFormat.Shape.STRING)
//    @JsonProperty("empDateOfBirth")
    private OffsetDateTime empDateOfBirth;

    //    @JsonProperty("empVehicle")
    private boolean empVehicle;

    //    @JsonProperty("empSupervisor")
    private Integer empSupervisor;
}
