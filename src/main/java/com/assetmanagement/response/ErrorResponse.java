package com.assetmanagement.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class ErrorResponse extends BaseResponse{
    private String errorCode;
    private Map<String, String> validationErrors;

    public ErrorResponse(final String errorCode, final String errorMessage, final Boolean success) {
        super(errorMessage, success);
        this.errorCode = errorCode;
    }

    public ErrorResponse(final Boolean success, final Map<String, String> errors) {
        super(success);
        this.validationErrors = errors;
    }
}
