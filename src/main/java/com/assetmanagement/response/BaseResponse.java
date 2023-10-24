package com.assetmanagement.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse implements Serializable {
    private String transactionId = UUID.randomUUID().toString();
    private String message;
    private Boolean success;

    public BaseResponse(final String message, final Boolean success){
        this.message = message;
        this.success = success;

    }

    public BaseResponse(final Boolean success) {
        this.success = success;
    }
}
