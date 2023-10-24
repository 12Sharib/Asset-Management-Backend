package com.assetmanagement.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AssetDto {
    private String typeName;
    private String brand;
    private Integer assetId;
    private Double price;
    private String description;
    private String status;
    private String allocatedTo;
    private String allocationDate;
    private String lastUpdate;
    private String warrantyEnd;
    private String vendorLocation;
}
