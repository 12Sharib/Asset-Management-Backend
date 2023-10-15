package com.assetmanagement.service.serviceImpl;

import com.assetmanagement.constants.AssetStatusConstants;
import com.assetmanagement.constants.MessageConstants;
import com.assetmanagement.convertor.EntityDtoConvertor;
import com.assetmanagement.dao.AssetRepository;
import com.assetmanagement.dao.CategoryRepository;
import com.assetmanagement.dao.CommonAttributesRepository;
import com.assetmanagement.dao.ManufacturingInfoRepository;
import com.assetmanagement.dao.MoreAttributesRepository;
import com.assetmanagement.dao.TypeRepository;
import com.assetmanagement.dto.AssetInputDto;
import com.assetmanagement.dto.ManufacturingInputDto;
import com.assetmanagement.entity.Asset;
import com.assetmanagement.entity.CommonAttributes;
import com.assetmanagement.entity.ManufacturingInfo;
import com.assetmanagement.entity.MoreAttributes;
import com.assetmanagement.enums.ErrorEnum;
import com.assetmanagement.exceptions.AssetManagementException;
import com.assetmanagement.response.AssetResponse;
import com.assetmanagement.response.ErrorResponse;
import com.assetmanagement.service.AssetService;
import com.assetmanagement.utils.DateTimeUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Log4j2
@Service
public class AssetServiceImpl implements AssetService {
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

    private void validateCategoryId(final Integer categoryId) {
        if(!categoryRepository.existsById(categoryId)){
            throw new AssetManagementException(new ErrorResponse(
                    ErrorEnum.INVALID_CATEGORY_ID.getErrorCode(), ErrorEnum.INVALID_CATEGORY_ID.getErrorMessage(), false
            ));
        }
    }

    private void validateTypeId(final Integer typeId) {
        if (!typeRepository.existsById(typeId)){
            throw new AssetManagementException(new ErrorResponse(
                    ErrorEnum.INVALID_TYPE_ID.getErrorCode(), ErrorEnum.INVALID_TYPE_ID.getErrorMessage(), false
            ));
        }
    }

    private void saveAsset(final Integer manufacturingInfoId, final Integer moreAttributesId,
                           final Integer commonAttributesId, final AssetInputDto assetInputDto) {
        final Asset newAsset = new Asset();
        newAsset.setStatus(AssetStatusConstants.ACTIVE);
        newAsset.setDescription(assetInputDto.getDescription());
        newAsset.setTypeId(assetInputDto.getTypeId());
        newAsset.setCategoriesId(assetInputDto.getCategoryId());
        newAsset.setManufacturingInfoId(manufacturingInfoId);
        newAsset.setMoreAttributeId(moreAttributesId);
        newAsset.setCommonAttributesId(commonAttributesId);
        newAsset.setIsActive(true);

        assetRepository.save(newAsset);
    }

    private Integer saveCommonAttributes(final CommonAttributes commonAttributesDetails) {
        final CommonAttributes commonAttributes = commonAttributesRepository.save(commonAttributesDetails);
        return commonAttributes.getId();
    }

    private Integer saveMoreAttributes(final MoreAttributes moreAttributesDetails) {
        final MoreAttributes moreAttributes = moreAttributesRepository.save(moreAttributesDetails);
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
