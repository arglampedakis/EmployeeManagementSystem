package com.cite.employee_managment.dto;

import lombok.*;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AttributeDto {
    private Integer attrId;
    private String attrName;
    private String attrValue;

}
