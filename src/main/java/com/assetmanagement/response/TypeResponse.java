package com.assetmanagement.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TypeResponse extends BaseResponse{
    public TypeResponse(final String message, final Boolean success){
        super(message, success);
    }
}
