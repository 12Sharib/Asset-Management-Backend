package com.assetmanagement.service.serviceImpl;

import com.assetmanagement.convertor.EntityDtoConvertor;
import com.assetmanagement.dao.AllocationRepository;
import com.assetmanagement.dao.AssetRepository;
import com.assetmanagement.dao.EmployeeRepository;
import com.assetmanagement.dto.AllocationDto;
import com.assetmanagement.dto.AllocationInputDto;
import com.assetmanagement.entity.Allocation;
import com.assetmanagement.entity.Asset;
import com.assetmanagement.enums.AssetStatusEnum;
import com.assetmanagement.enums.ErrorEnum;
import com.assetmanagement.exceptions.AssetManagementException;
import com.assetmanagement.response.AllocationResponse;
import com.assetmanagement.response.ErrorResponse;
import com.assetmanagement.service.AllocationService;
import com.assetmanagement.utils.DateTimeUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class AllocationServiceImpl implements AllocationService {
    @Autowired
    private AssetRepository assetRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private AllocationRepository allocationRepository;
    @Autowired
    private EntityDtoConvertor entityDtoConvertor;


    @Override
    public AllocationResponse allocate(final AllocationInputDto allocationInputDto) {
        log.info("Started allocate resource service");
        // Validate format of date
        DateTimeUtil.validateDateFormat(allocationInputDto.getStartDate());
        // Validate date days, year and month
        DateTimeUtil.validateYearsMonthsDays(allocationInputDto.getStartDate());

        // Validate End date if not null
        if (allocationInputDto.getEndDate() != null){
            // Validate format of date
            DateTimeUtil.validateDateFormat(allocationInputDto.getEndDate());
            //Validate date days, years and month
            DateTimeUtil.validateYearsMonthsDays(allocationInputDto.getEndDate());
        }
        // Validate allocation start date should not be before the current date
        validateAllocationStartDateWithCurrentDate(allocationInputDto.getStartDate());
        // Validate asset id
        validateAssetId(allocationInputDto.getAssetId());
        // Validate employee id
        validateEmployeeId(allocationInputDto.getEmployeeId());
        // Validate for if asset is already allocated
        validateAssetIsAlreadyAllocated(allocationInputDto.getAssetId());

        // Allocate Asset to resource
        log.info("Started saving allocation");
        final Allocation allocation = getSavedAllocationFromDto(allocationInputDto);

        // Update Status of Asset
        updateStatusOfAsset(allocationInputDto.getAssetId());
        log.info("Completed saving allocation");

        log.info("Completed allocate resource service");
        // Response
        return new AllocationResponse(true, entityDtoConvertor.convertAllocationToDto(allocation));
    }

    private void updateStatusOfAsset(final Integer assetId) {
        log.info("Started updating of asset status");
        final Optional<Asset> optionalAsset = assetRepository.findById(assetId);
        if (optionalAsset.isPresent()){
            final Asset asset = optionalAsset.get();
            asset.setStatus(AssetStatusEnum.ALLOCATED.getStatus());

            // Save Updated Asset Status
            assetRepository.save(asset);
            log.info("Completed updating of asset status");
        }
    }

    @Override
    public AllocationResponse allocations() {
        log.info("Started get all allocations service");
        final List<Allocation> allocations = allocationRepository.findAllActiveAllocations();
        final List<AllocationDto> allocationDtos = new ArrayList<>();

        allocations.forEach(allocation ->
            allocationDtos.add(entityDtoConvertor.convertAllocationToDto(allocation))
        );

        log.info("Completed get all allocations service");
        return new AllocationResponse(true, allocationDtos);
    }

    private void validateAssetIsAlreadyAllocated(final Integer assetId) {
        if (allocationRepository.existsByAssetId(assetId)){
            throw new AssetManagementException(new ErrorResponse(
                    ErrorEnum.INVALID_ASSET_ID_ALREADY_ALLOCATED.getErrorCode(), ErrorEnum.INVALID_ASSET_ID_ALREADY_ALLOCATED.getErrorMessage(), false
            ));
        }
    }

    private void validateAllocationStartDateWithCurrentDate(final String startDate) {
        final Date date = new Date();
        String currentDate = DateTimeUtil.convertDateToString(date);

        if (DateTimeUtil.convertStringToDate(currentDate).compareTo(DateTimeUtil.convertStringToDate(startDate)) > 0) {
            throw new AssetManagementException(new ErrorResponse(
                    ErrorEnum.INVALID_ALLOCATION_START_DATE.getErrorCode(), ErrorEnum.INVALID_ALLOCATION_START_DATE.getErrorMessage(), false
            ));
        }
    }

    private Allocation getSavedAllocationFromDto(final AllocationInputDto allocationInputDto) {
        final Allocation allocation = new Allocation();
        allocation.setStartDate(DateTimeUtil.convertStringToDate(allocationInputDto.getStartDate()));
        if(allocationInputDto.getEndDate() != null){
            allocation.setEndDate(DateTimeUtil.convertStringToDate(allocationInputDto.getEndDate()));
        }else {
            allocation.setEndDate(null);
        }
        allocation.setAssetId(allocationInputDto.getAssetId());
        allocation.setEmployeeId(allocationInputDto.getEmployeeId());
        allocation.setIsActive(true);

        return allocationRepository.save(allocation);
    }

    private void validateEmployeeId(final String employeeId) {
        if (!employeeRepository.existsById(employeeId)){
            throw new AssetManagementException(new ErrorResponse(
                    ErrorEnum.INVALID_EMPLOYEE_ID.getErrorCode(), ErrorEnum.INVALID_EMPLOYEE_ID.getErrorMessage(), false
            ));
        }
    }

    public void validateAssetId(final Integer id){
        if (!assetRepository.existsById(id)){
            throw new AssetManagementException(new ErrorResponse(
                    ErrorEnum.INVALID_ASSET_ID.getErrorCode(), ErrorEnum.INVALID_ASSET_ID.getErrorMessage(), false
            ));
        }
    }
}
