package com.assetmanagement.service.serviceImpl;

import com.assetmanagement.constants.AssetStatusConstants;
import com.assetmanagement.constants.MessageConstants;
import com.assetmanagement.convertor.EntityDtoConvertor;
import com.assetmanagement.dao.AllocationRepository;
import com.assetmanagement.dao.AssetRepository;
import com.assetmanagement.dao.CategoryRepository;
import com.assetmanagement.dao.CommonAttributesRepository;
import com.assetmanagement.dao.ManufacturingInfoRepository;
import com.assetmanagement.dao.MoreAttributesRepository;
import com.assetmanagement.dao.TypeRepository;
import com.assetmanagement.dto.AssetDto;
import com.assetmanagement.dto.AssetInputDto;
import com.assetmanagement.dto.CommonAttributesInputDto;
import com.assetmanagement.dto.ManufacturingInputDto;
import com.assetmanagement.dto.MoreAttributesInputDto;
import com.assetmanagement.entity.Allocation;
import com.assetmanagement.entity.Asset;
import com.assetmanagement.entity.Categories;
import com.assetmanagement.entity.CommonAttributes;
import com.assetmanagement.entity.ManufacturingInfo;
import com.assetmanagement.entity.MoreAttributes;
import com.assetmanagement.entity.Type;
import com.assetmanagement.enums.AssetStatusEnum;
import com.assetmanagement.enums.ErrorEnum;
import com.assetmanagement.exceptions.AssetManagementException;
import com.assetmanagement.response.AssetResponse;
import com.assetmanagement.response.ErrorResponse;
import com.assetmanagement.service.AssetService;
import com.assetmanagement.utils.DateTimeUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class AssetServiceImpl implements AssetService {
    @Autowired
    private AllocationRepository allocationRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private TypeRepository typeRepository;
    @Autowired
    private AssetRepository assetRepository;
    @Autowired
    private CommonAttributesRepository commonAttributesRepository;
    @Autowired
    private MoreAttributesRepository moreAttributesRepository;
    @Autowired
    private ManufacturingInfoRepository manufacturingInfoRepository;
    @Autowired
    private EntityDtoConvertor entityDtoConvertor;

    @Override
    public AssetResponse addAsset(final AssetInputDto assetInputDto) {
        log.info("Started add asset service");

        //validate type id
        validateTypeId(assetInputDto.getTypeId());

        //validate category id
        validateCategoryId(assetInputDto.getCategoryId());

        log.info("Started saving manufacturing information");
        //save manufacturing info
        final Integer manufacturingInfoId = saveManufacturingInfo(assetInputDto.getManufacturingInfo());
        log.info("Completed saving manufacturing information");

        log.info("Started saving more attributes");
        //save more attributes
        final Integer moreAttributesId = saveMoreAttributes(assetInputDto.getMoreAttributes());
        log.info("Completed saving more attributes");

        log.info("Started saving common attributes");
        //save common attributes
        final Integer commonAttributesId = saveCommonAttributes(assetInputDto.getCommonAttributes());
        log.info("Completed saving common attributes");

        log.info("Started saving asset");
        //save asset in database
        saveAsset(manufacturingInfoId, moreAttributesId, commonAttributesId, assetInputDto);
        log.info("Completed saving asset");

        //return success response after saving new asset
        return new AssetResponse(MessageConstants.ASSET_SAVED, true);
    }

    @Override
    public AssetResponse allAssets() {
        log.info("Started get all assets service");
        // All active assets
        final List<Asset> assets = assetRepository.findAllActiveAssets();

        // Get an asset dto list from assets
        final List<AssetDto> assetDtos  = getAssetDtoFromAssets(assets);

        log.info("Completed get all assets service");
        return new AssetResponse(true, assetDtos);
    }

    private List<AssetDto> getAssetDtoFromAssets(final List<Asset> assets) {
        final List<Categories> categories = categoryRepository.findAll();
        final List<Type> types = typeRepository.findAll();
        final List<ManufacturingInfo> manufacturingInfos = manufacturingInfoRepository.findAll();
        final List<CommonAttributes> commonAttributes = commonAttributesRepository.findAll();
        final List<MoreAttributes> moreAttributes = moreAttributesRepository.findAll();
        final List<Allocation> allocations = allocationRepository.findAll();

        final List<AssetDto> assetDtos = new ArrayList<>();
        // Iterate to convert asset to dto
        assets.forEach(asset -> {
            final AssetDto assetDto = new AssetDto();

            final Optional<Type> optionalType = types.stream().filter(type -> type.getId().equals(asset.getTypeId())).findFirst();
            final Optional<Categories> optionalCategory = categories.stream().filter(category -> category.getId().equals(asset.getCategoriesId())).findFirst();
            final Optional<ManufacturingInfo> optionalManufacturingInfo = manufacturingInfos.stream().filter(manufacturingInfo -> manufacturingInfo.getId().equals(asset.getManufacturingInfoId())).findFirst();
            final Optional<MoreAttributes> optionalMoreAttribute = moreAttributes.stream().filter(moreAttribute -> moreAttribute.getId().equals(asset.getMoreAttributeId())).findFirst();
            final Optional<CommonAttributes> optionalCommonAttribute = commonAttributes.stream().filter(commonAttribute -> commonAttribute.getId().equals(asset.getCommonAttributesId())).findFirst();
            final Optional<Allocation> optionalAllocation = allocations.stream().filter(allocation -> allocation.getAssetId().equals(asset.getId())).findFirst();

            // Set all details in dto
            if (optionalType.isPresent() && optionalCategory.isPresent()
                    && optionalManufacturingInfo.isPresent()
                    && optionalCommonAttribute.isPresent()
                    && optionalMoreAttribute.isPresent()) {

                assetDto.setTypeName(optionalType.get().getName());
                assetDto.setBrand(optionalManufacturingInfo.get().getBrand());
                assetDto.setAssetId(asset.getId());
                assetDto.setManufacturingInfoDto(entityDtoConvertor.convertManufacturingInfoToDto(optionalManufacturingInfo.get()));
                assetDto.setCommonAttributesDto(entityDtoConvertor.convertCommonAttributesToDto(optionalCommonAttribute.get()));
                assetDto.setMoreAttributesDto(entityDtoConvertor.convertMoreAttributesToDto(optionalMoreAttribute.get()));
            }
            if (optionalAllocation.isPresent()) {
                assetDto.setAllocatedTo(optionalAllocation.get().getEmployeeId());
                assetDto.setAllocationDate(DateTimeUtil.convertDateToString(optionalAllocation.get().getStartDate()));
            } else {
                assetDto.setAllocatedTo(null);
                assetDto.setAllocationDate(null);
            }
            // Last Update from history table
            assetDto.setLastUpdate(null);

            assetDtos.add(assetDto);
        });
        return assetDtos;
    }

    @Override
    public AssetResponse updateStatus(final Integer assetId, final Integer statusId) {
        log.info("Started update asset status service");
        // Validate asset id
        validateAssetId(assetId);
        // Validate status id
        if (getAssetStatus(statusId) == null) {
            throw new AssetManagementException(new ErrorResponse(
                    ErrorEnum.INVALID_ASSET_STATUS_CODE.getErrorCode(), ErrorEnum.INVALID_ASSET_STATUS_CODE.getErrorMessage(), false
            ));
        }
        // Update status of the asset
        final Optional<Asset> optionalAsset = assetRepository.findById(assetId);
        log.info("Started updating asset status");
        if (optionalAsset.isPresent()) {
            final Asset asset = optionalAsset.get();
            asset.setStatus(AssetStatusEnum.DE_COMMISSIONED.getStatus());
            assetRepository.save(asset);
            log.info("Completed updating asset status");
        }
        log.info("Completed update asset status service");
        return new AssetResponse(true, MessageConstants.STATUS_UPDATED_SUCCESSFULLY);
    }

    @Override
    public AssetResponse assetsByCategory(Integer categoryId) {
        log.info("Started get all assets by category service");
        // Validate category id
        validateCategoryId(categoryId);

        // Get all assets of this category
        final List<Asset> assets = assetRepository.findByCategoryId(categoryId);

        // Get an asset dto list from assets
        final List<AssetDto> assetDtos = getAssetDtoFromAssets(assets);

        log.info("Completed get all assets by category service");
        return new AssetResponse(true, assetDtos);
    }

    private void validateAssetId(final Integer assetId) {
        if (!assetRepository.existsById(assetId)) {
            throw new AssetManagementException(new ErrorResponse(
                    ErrorEnum.INVALID_ASSET_ID.getErrorCode(), ErrorEnum.INVALID_ASSET_ID.getErrorMessage(), false
            ));
        }
    }

    private String getAssetStatus(final Integer status) {
        return switch (status) {
            case 1 -> AssetStatusConstants.ACTIVE;
            case 2 -> AssetStatusConstants.ALLOCATED;
            case 3 -> AssetStatusConstants.DE_COMMISSIONED;
            case 4 -> AssetStatusConstants.IN_ACTIVE;
            case 5 -> AssetStatusConstants.DEFECTIVE;
            default -> null;
        };
    }

    private void validateCategoryId(final Integer categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new AssetManagementException(new ErrorResponse(
                    ErrorEnum.INVALID_CATEGORY_ID.getErrorCode(), ErrorEnum.INVALID_CATEGORY_ID.getErrorMessage(), false
            ));
        }
    }

    private void validateTypeId(final Integer typeId) {
        if (!typeRepository.existsById(typeId)) {
            throw new AssetManagementException(new ErrorResponse(
                    ErrorEnum.INVALID_TYPE_ID.getErrorCode(), ErrorEnum.INVALID_TYPE_ID.getErrorMessage(), false
            ));
        }
    }

    private void saveAsset(final Integer manufacturingInfoId, final Integer moreAttributesId,
                           final Integer commonAttributesId, final AssetInputDto assetInputDto) {
        final Asset newAsset = new Asset();
        newAsset.setStatus(AssetStatusEnum.ACTIVE.getStatus());
        newAsset.setDescription(assetInputDto.getDescription());
        newAsset.setTypeId(assetInputDto.getTypeId());
        newAsset.setCategoriesId(assetInputDto.getCategoryId());
        newAsset.setManufacturingInfoId(manufacturingInfoId);
        newAsset.setMoreAttributeId(moreAttributesId);
        newAsset.setCommonAttributesId(commonAttributesId);
        newAsset.setIsActive(true);

        assetRepository.save(newAsset);
    }

    private Integer saveCommonAttributes(final CommonAttributesInputDto commonAttributesDetails) {
        final CommonAttributes newCommonAttributesDetails = new CommonAttributes();
        newCommonAttributesDetails.setColour(commonAttributesDetails.getColour());
        newCommonAttributesDetails.setModelNumber(commonAttributesDetails.getModelNumber());
        newCommonAttributesDetails.setSerialNumber(commonAttributesDetails.getSerialNumber());
        newCommonAttributesDetails.setSku(commonAttributesDetails.getSku());

        final CommonAttributes commonAttributes = commonAttributesRepository.save(newCommonAttributesDetails);
        return commonAttributes.getId();
    }

    private Integer saveMoreAttributes(final MoreAttributesInputDto moreAttributesDetails) {
        final MoreAttributes newMoreAttributesDetails = new MoreAttributes();
        newMoreAttributesDetails.setRam(moreAttributesDetails.getRam());
        newMoreAttributesDetails.setHardDisk(moreAttributesDetails.getHardDisk());
        newMoreAttributesDetails.setScreenSize(moreAttributesDetails.getScreenSize());
        newMoreAttributesDetails.setOperatingSystem(moreAttributesDetails.getOperatingSystem());

        final MoreAttributes moreAttributes = moreAttributesRepository.save(newMoreAttributesDetails);
        return moreAttributes.getId();
    }

    private Integer saveManufacturingInfo(final ManufacturingInputDto manufacturingInfoDetails) {
        //validate manufacturing date format
        DateTimeUtil.validateDateFormat(manufacturingInfoDetails.getManufacturingDate());
        //validate year, month and days of date
        DateTimeUtil.validateYearsMonthsDays(manufacturingInfoDetails.getManufacturingDate());

        //validate warranty start date format
        DateTimeUtil.validateDateFormat(manufacturingInfoDetails.getWarrantyStart());
        //validate year, month and days of date
        DateTimeUtil.validateYearsMonthsDays(manufacturingInfoDetails.getWarrantyStart());

        //validate warranty end date format
        DateTimeUtil.validateDateFormat(manufacturingInfoDetails.getWarrantyEnd());
        //validate year, month and days of date
        DateTimeUtil.validateYearsMonthsDays(manufacturingInfoDetails.getWarrantyEnd());

        //validate warranty start date does not come after end date
        validateWarrantyStartEndDate(manufacturingInfoDetails.getWarrantyStart(), manufacturingInfoDetails.getWarrantyEnd());

        final ManufacturingInfo newManufacturingInfo = entityDtoConvertor.convertManufacturingInfoDtoToEntity(manufacturingInfoDetails);
        final ManufacturingInfo savedManufacturingInfo = manufacturingInfoRepository.save(newManufacturingInfo);

        return savedManufacturingInfo.getId();
    }

    private void validateWarrantyStartEndDate(final String warrantyStart, final String warrantyEnd) {
        if ((DateTimeUtil.convertStringToDate(warrantyStart).compareTo(DateTimeUtil.convertStringToDate(warrantyEnd))) > 0) {
            throw new AssetManagementException(new ErrorResponse(
                    ErrorEnum.INVALID_START_DATE.getErrorCode(), ErrorEnum.INVALID_START_DATE.getErrorMessage(), false
            ));
        }
    }
}
