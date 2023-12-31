package com.assetmanagement.enums;

import lombok.Getter;

@Getter
public enum ErrorEnum {
    //Category error code starts 100
    //Type error code starts 200
    //date error 500
    //Asset error 300
    //Employee error 400
    //Allocation error 600
    INVALID_CATEGORY_NAME("100-001", "Invalid category name, provide valid name"),
    EXISTING_CATEGORY_NAME("100-002", "Invalid category name, name is already exists"),
    INVALID_CATEGORY_ID("100-003", "Invalid category id, provide valid category id"),

    INVALID_TYPE_NAME("200-001", "Invalid type name, provide valid type name"),
    EXISTING_TYPE_NAME("200-002", "Invalid type name, name is already exists"),
    INVALID_TYPE_ID("200-003", "Invalid type id, provide valid type id"),

    INVALID_ASSET_ID("300-001", "Invalid asset id, provide valid asset id"),
    INVALID_ASSET_STATUS_CODE("300-002", "Invalid asset status id, provide valid status id"),

    INVALID_EMPLOYEE_ID("400-001", "Invalid employee id, provide valid employee id"),

    UNABLE_TO_CONVERT_STRING_TO_DATE("500-001", "Unable to parse date to string date"),
    INVALID_DATE_FORMAT("500-002", "Invalid date format, provide valid date format i.e., yyyy-MM-dd"),
    INVALID_START_DATE("500-003", "Invalid start date, start date occurs after end date"),
    INVALID_YEAR("500-004","Invalid year, year must be exist between 2000 to 2100"),
    INVALID_MONTHS("500-005", "Invalid month, month must be exist between 1 to 12"),
    INVALID_DAYS("500-006", "Invalid days, days must be exist between 1 to 30 or 1 to 31"),
    INVALID_FEB_MONTH("500-007", "Invalid days in feb days must be exist between 1 to 28 or 1 to 29"),

    INVALID_ALLOCATION_START_DATE("600-001", "Invalid allocation start date, should be same as current date or after current date"),
    INVALID_ASSET_ID_ALREADY_ALLOCATED("600-002","Invalid asset id, asset is already allocated");

    private final String errorCode;
    private final String errorMessage;

    ErrorEnum(final String errorCode, final String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
