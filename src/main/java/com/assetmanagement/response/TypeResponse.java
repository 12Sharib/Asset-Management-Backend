package com.assetmanagement.response;

import com.assetmanagement.dto.TypeDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TypeResponse extends BaseResponse{
    List<TypeDto> typeDtos;
    public TypeResponse(final String message, final Boolean success){
        super(message, success);
    }

    public TypeResponse(final Boolean success, final List<TypeDto> typeDtos) {
        super(success);
        this.typeDtos = typeDtos;
    }
}
