package com.assetmanagement.service;

import com.assetmanagement.dto.TypeInputDto;
import com.assetmanagement.response.TypeResponse;

public interface TypeService {
    TypeResponse addType(TypeInputDto typeInputDto);
}
