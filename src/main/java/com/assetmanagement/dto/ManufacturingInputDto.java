package com.assetmanagement.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ManufacturingInputDto {
    @NotBlank(message = "Brand name shouldn't be blank, provide valid brand name")
    private String brand;

    @NotBlank(message = "Manufacturing date shouldn't be blank, provide valid manufacturing date")
    private String manufacturingDate;

    @NotNull(message = "Price shouldn't be blank or null, provide valid price")
    @Min(value = 500,message = "Price shouldn't be less than 500")
    private Double price;

    @NotBlank(message = "Warranty start shouldn't be blank, provide valid warranty start date")
    private String warrantyStart;

    @NotBlank(message = "Warranty end shouldn't be blank, provide valid warranty end date")
    private String warrantyEnd;

    @NotNull(message = "Vendor Id shouldn't be empty or null, provide valid vendor id")
    private Integer vendorId;

    @NotBlank(message = "Vendor name shouldn't be blank, provide valid vendor name")
    private String vendorName;

    @NotBlank(message = "Vendor location shouldn't be blank, provide valid vendor location")
    private String vendorLocation;
}
