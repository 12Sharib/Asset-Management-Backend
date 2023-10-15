package com.assetmanagement.dto;

import com.assetmanagement.entity.CommonAttributes;
import com.assetmanagement.entity.ManufacturingInfo;
import com.assetmanagement.entity.MoreAttributes;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssetInputDto {
    @NotNull(message = "Type can't be blank or empty, provide valid asset type id")
    private Integer typeId;
    @NotNull(message = "Category can't be blank or empty, provide valid asset category")
    private Integer categoryId;
    @Valid
    private ManufacturingInputDto manufacturingInfo;
    @Valid
    private MoreAttributes moreAttributes;
    @Valid
    private CommonAttributes commonAttributes;
    @NotBlank(message = "Description can't be blank or empty, provide some description of asset")
    private String description;
}
