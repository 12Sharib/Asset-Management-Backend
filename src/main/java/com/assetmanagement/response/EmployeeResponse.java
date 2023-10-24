package com.assetmanagement.response;

import com.assetmanagement.dto.EmployeeDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EmployeeResponse extends BaseResponse{
    List<EmployeeDto> employeeDtos;
    public EmployeeResponse(final Boolean success, final List<EmployeeDto> employeeDtos) {
        super(success);
        this.employeeDtos = employeeDtos;
    }
}
