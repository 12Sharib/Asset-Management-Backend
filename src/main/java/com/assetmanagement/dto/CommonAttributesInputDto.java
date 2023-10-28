package com.assetmanagement.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonAttributesInputDto {
    @NotBlank(message = "Provide valid serial number of an asset, length must be equal to 10")
    private String serialNumber;

    @NotBlank(message = "Provide valid model number of an asset, length must be equal to 10")
    private String modelNumber;

    @NotBlank(message = "Provide valid SKU, this shouldn't be blank")
    private String sku;

    @NotBlank(message = "Provide valid colour, colour shouldn't be blank")
    private String colour;
}
