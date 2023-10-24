package com.assetmanagement.service;

import com.assetmanagement.dto.AssetInputDto;
import com.assetmanagement.entity.Asset;
import com.assetmanagement.response.AssetResponse;

public interface AssetService {
    AssetResponse addAsset(AssetInputDto assetInputDto);

    AssetResponse allAssets();

    AssetResponse updateStatus(Integer assetId, Integer statusId);
}
