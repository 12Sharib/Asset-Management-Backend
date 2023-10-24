package com.assetmanagement.service;

import com.assetmanagement.dto.AllocationInputDto;
import com.assetmanagement.response.AllocationResponse;
import org.springframework.stereotype.Service;

@Service
public interface AllocationService {
    AllocationResponse allocate(AllocationInputDto allocationInputDto);

    AllocationResponse allocations();
}
