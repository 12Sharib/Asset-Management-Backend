package com.assetmanagement.service;

import com.assetmanagement.dto.CategoryInputDto;
import com.assetmanagement.entity.Categories;
import com.assetmanagement.exceptions.AssetManagementException;
import com.assetmanagement.response.CategoryResponse;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {
    CategoryResponse addCategory(CategoryInputDto category);

    CategoryResponse getAllCategories();
}
