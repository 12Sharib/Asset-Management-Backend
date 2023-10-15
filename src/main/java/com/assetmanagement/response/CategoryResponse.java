package com.assetmanagement.response;

import lombok.NoArgsConstructor;

public class CategoryResponse extends BaseResponse{
    public CategoryResponse(final String message, final Boolean success) {
        super(message,success);
    }
}
