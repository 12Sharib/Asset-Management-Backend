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
    private String allocatedTo;
    private String allocationDate;
    private String lastUpdate;
    private ManufacturingInfoDto manufacturingInfoDto;
    private MoreAttributesDto moreAttributesDto;
    private CommonAttributesDto commonAttributesDto;
}
