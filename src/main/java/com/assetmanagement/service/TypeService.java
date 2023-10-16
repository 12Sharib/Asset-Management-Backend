package com.assetmanagement.service;

import com.assetmanagement.dto.TypeInputDto;
import com.assetmanagement.response.TypeResponse;

public interface TypeService {
    TypeResponse addType(TypeInputDto typeInputDto);

    TypeResponse delete(Integer typeId);

    TypeResponse getAllActiveTypes();

    TypeResponse updateDetails(TypeInputDto typeInputDto, Integer typeId);
}
