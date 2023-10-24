package com.assetmanagement.response;

import com.assetmanagement.dto.CategoryDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CategoryResponse extends BaseResponse{
    List<CategoryDto> categoryDtos;
    public CategoryResponse(final String message, final Boolean success) {
        super(message,success);
    }

    public CategoryResponse(final Boolean success, final List<CategoryDto> categoryDtos) {
        super(success);
        this.categoryDtos = categoryDtos;
    }
}
