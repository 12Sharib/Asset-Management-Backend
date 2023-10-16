package com.assetmanagement.convertor;

import com.assetmanagement.dto.CategoryDto;
import com.assetmanagement.dto.ManufacturingInputDto;
import com.assetmanagement.dto.TypeDto;
import com.assetmanagement.entity.Categories;
import com.assetmanagement.entity.ManufacturingInfo;
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
    public TypeDto convertTypeToDto(final Type type){
        return modelMapper.map(type, TypeDto.class);
    }
    public CategoryDto convertCategoryToDto(final Categories category){
        return modelMapper.map(category, CategoryDto.class);
    }
}
