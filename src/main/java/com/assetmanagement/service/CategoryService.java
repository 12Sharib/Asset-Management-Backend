package com.assetmanagement.service;

import com.assetmanagement.dto.CategoryInputDto;
import com.assetmanagement.entity.Categories;
import com.assetmanagement.exceptions.AssetManagementException;
import com.assetmanagement.response.CategoryResponse;

public interface CategoryService {
    CategoryResponse addCategory(CategoryInputDto category) throws AssetManagementException;

    CategoryResponse getAllCategories();
}
