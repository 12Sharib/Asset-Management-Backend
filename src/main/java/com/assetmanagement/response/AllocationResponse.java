package com.assetmanagement.response;

import com.assetmanagement.dto.AllocationDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AllocationResponse extends BaseResponse{
    private AllocationDto allocationDto;
    private List<AllocationDto> allocationDtos;
    public AllocationResponse(final Boolean success, final AllocationDto allocationDto) {
        super(success);
        this.allocationDto = allocationDto;
    }

    public AllocationResponse(final Boolean success, final List<AllocationDto> allocationDtos) {
        super(success);
        this.allocationDtos = allocationDtos;
    }
}
