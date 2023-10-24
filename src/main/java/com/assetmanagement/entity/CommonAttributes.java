package com.assetmanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "common_attributes")
public class CommonAttributes {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "serial_number")
    @NotBlank(message = "Provide valid serial number of an asset, length must be equal to 10")
    private String serialNumber;

    @Column(name = "model_number")
    @NotBlank(message = "Provide valid model number of an asset, length must be equal to 10")
    private String modelNumber;

    @Column(name = "sku")
    @NotBlank(message = "Provide valid SKU, this shouldn't be blank")
    private String sku;

    @Column(name = "colour")
    @NotBlank(message = "Provide valid colour, colour shouldn't be blank")
    private String colour;
}
