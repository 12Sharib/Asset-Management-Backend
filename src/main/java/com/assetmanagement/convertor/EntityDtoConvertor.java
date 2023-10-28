package com.assetmanagement.convertor;

import com.assetmanagement.dto.AllocationDto;
import com.assetmanagement.dto.CategoryDto;
import com.assetmanagement.dto.CommonAttributesDto;
import com.assetmanagement.dto.ManufacturingInfoDto;
import com.assetmanagement.dto.ManufacturingInputDto;
import com.assetmanagement.dto.MoreAttributesDto;
import com.assetmanagement.dto.TypeDto;
import com.assetmanagement.entity.Allocation;
import com.assetmanagement.entity.Categories;
import com.assetmanagement.entity.CommonAttributes;
import com.assetmanagement.entity.ManufacturingInfo;
import com.assetmanagement.entity.MoreAttributes;
import com.assetmanagement.entity.Type;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EntityDtoConvertor {
    @Autowired
    private ModelMapper modelMapper;
    public ManufacturingInfo convertManufacturingInfoDtoToEntity(final ManufacturingInputDto manufacturingInputDto){
        return modelMapper.map(manufacturingInputDto, ManufacturingInfo.class);
    }
    public ManufacturingInfoDto convertManufacturingInfoToDto(final ManufacturingInfo manufacturingInfo){
        return modelMapper.map(manufacturingInfo, ManufacturingInfoDto.class);
    }
    public CommonAttributesDto convertCommonAttributesToDto(final CommonAttributes commonAttributes){
        return modelMapper.map(commonAttributes, CommonAttributesDto.class);
    }
    public MoreAttributesDto convertMoreAttributesToDto(final MoreAttributes moreAttributes){
        return modelMapper.map(moreAttributes, MoreAttributesDto.class);
    }
    public TypeDto convertTypeToDto(final Type type){
        return modelMapper.map(type, TypeDto.class);
    }
    public CategoryDto convertCategoryToDto(final Categories category){
        return modelMapper.map(category, CategoryDto.class);
    }
    public AllocationDto convertAllocationToDto(final Allocation allocation){
        return modelMapper.map(allocation, AllocationDto.class);
    }

}
