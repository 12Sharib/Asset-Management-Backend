package com.assetmanagement.convertor;

import com.assetmanagement.dto.ManufacturingInputDto;
import com.assetmanagement.entity.ManufacturingInfo;
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
}
