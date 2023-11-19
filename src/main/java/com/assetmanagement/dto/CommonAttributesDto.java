package com.assetmanagement.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonAttributesDto {
    private String serialNumber;
    private String modelNumber;
    private String sku;
    private String colour;
}
