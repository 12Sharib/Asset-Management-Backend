package com.assetmanagement.exceptions;

import com.assetmanagement.response.ErrorResponse;
import lombok.Getter;
import lombok.ToString;

@Getter
public class AssetManagementException extends RuntimeException{
    private final ErrorResponse errorResponse;

    public AssetManagementException(ErrorResponse response){
        this.errorResponse = response;
    }
}
