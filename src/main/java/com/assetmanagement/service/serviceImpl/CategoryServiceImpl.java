package com.assetmanagement.service.serviceImpl;

import com.assetmanagement.constants.MessageConstants;
import com.assetmanagement.dao.CategoryRepository;
import com.assetmanagement.dto.CategoryInputDto;
import com.assetmanagement.entity.Categories;
import com.assetmanagement.enums.ErrorEnum;
import com.assetmanagement.exceptions.AssetManagementException;
import com.assetmanagement.response.CategoryResponse;
import com.assetmanagement.response.ErrorResponse;
import com.assetmanagement.service.CategoryService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public CategoryResponse addCategory(final CategoryInputDto categoryInputDto) throws AssetManagementException {
        log.info("Started service of add category service");

        //validate the name of category
        if(!validateCategoryName(categoryInputDto.getName())){
            throw new AssetManagementException(new ErrorResponse(
                    ErrorEnum.INVALID_CATEGORY_NAME.getErrorCode(), ErrorEnum.INVALID_CATEGORY_NAME.getErrorMessage(),false
            ));
        }
        //validate name exists or not
        validateExistenceOfName(categoryInputDto.getName());

        log.info("saving category");
        categoryRepository.save(getCategoryFromDto(categoryInputDto));
        log.info("saved category");

        log.info("Completed service of add category service");
        return new CategoryResponse(MessageConstants.CATEGORY_SAVED,true);
    }

    private void validateExistenceOfName(final String name) {
        final Boolean isExist = categoryRepository.existsByName(name);
        if (isExist){
            throw new AssetManagementException(new ErrorResponse(
                 ErrorEnum.EXISTING_CATEGORY_NAME.getErrorCode(), ErrorEnum.EXISTING_CATEGORY_NAME.getErrorMessage(), false
            ));
        }
    }

    private Categories getCategoryFromDto(final CategoryInputDto categoryInputDto) {
        final Categories category = new Categories();
        category.setName(categoryInputDto.getName());
        category.setIsActive(true);

        return category;
    }

    private Boolean validateCategoryName(final String name) {
        try{
            Integer.parseInt(name);
        }catch (final NumberFormatException exception){
            log.error("Invalid Category Name: ", exception);
            return true;
        }
        return false;
    }
}
