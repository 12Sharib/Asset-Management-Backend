package com.assetmanagement.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AllocationDto {
    private Integer id;
    private String startDate;
    private String endDate;
    private String employeeId;
    private String assetId;
}
