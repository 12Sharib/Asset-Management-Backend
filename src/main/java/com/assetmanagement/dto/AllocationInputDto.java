package com.assetmanagement.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AllocationInputDto {
    private String startDate;
    private String endDate;

    @NotNull(message = "Invalid asset id, id shouldn't be blank or empty")
    private Integer assetId;

    @NotBlank(message = "Invalid employee id, id shouldn't be blank or empty")
    private String employeeId;
}
