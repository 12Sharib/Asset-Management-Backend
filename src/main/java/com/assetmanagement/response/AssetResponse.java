package com.assetmanagement.response;

import com.assetmanagement.dto.AssetDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AssetResponse extends BaseResponse{
    List<AssetDto> assetDtos;
    public AssetResponse(String message, Boolean success) {
        super(message, success);
    }

    public AssetResponse(final Boolean success, final List<AssetDto> assetDtos) {
        super(success);
        this.assetDtos = assetDtos;
    }

    public AssetResponse(final Boolean success, final String message) {
        super(message, success);
    }
}
