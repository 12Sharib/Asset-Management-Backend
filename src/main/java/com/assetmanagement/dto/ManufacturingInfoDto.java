package com.assetmanagement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManufacturingInfoDto {
    private String brand;
    private String manufacturingDate;
    private Double price;
    private String warrantyStart;
    private String warrantyEnd;
    private String vendorLocation;
}
