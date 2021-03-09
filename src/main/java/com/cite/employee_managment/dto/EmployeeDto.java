package com.cite.employee_managment.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@AllArgsConstructor
public class EmployeeDto {

    private Integer empId;

    private String empName;

    private LocalDateTime empDateOfBirth;

    private boolean empVehicle;

    private Integer empSupervisor;
}
