package com.assetmanagement.enums;

import lombok.Getter;

@Getter
public enum AssetStatusEnum {
    ACTIVE(1),
    ALLOCATED(2),
    DE_COMMISSIONED(3),
    IN_ACTIVE(4),
    DEFECTIVE(5);

    final Integer status;
    AssetStatusEnum(Integer status){
        this.status = status;
    }
}
