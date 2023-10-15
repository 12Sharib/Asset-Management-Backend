package com.assetmanagement.response;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public class AssetResponse extends BaseResponse{
    public AssetResponse(String message, Boolean success) {
        super(message, success);
    }
}
