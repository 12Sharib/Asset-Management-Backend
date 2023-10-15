package com.assetmanagement.service.serviceImpl;

import com.assetmanagement.constants.MessageConstants;
import com.assetmanagement.dao.TypeRepository;
import com.assetmanagement.dto.TypeInputDto;
import com.assetmanagement.entity.Type;
import com.assetmanagement.enums.ErrorEnum;
import com.assetmanagement.exceptions.AssetManagementException;
import com.assetmanagement.response.ErrorResponse;
import com.assetmanagement.response.TypeResponse;
import com.assetmanagement.service.TypeService;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeRepository typeRepository;
    @Override
    public TypeResponse addType(final TypeInputDto typeInputDto) {
        log.info("Started add type service");
        //validate type name
        if(!validateTypeName(typeInputDto.getName())){
            throw new AssetManagementException(new ErrorResponse(
                    ErrorEnum.INVALID_TYPE_NAME.getErrorCode(), ErrorEnum.INVALID_TYPE_NAME.getErrorMessage(), false
            ));
        };
        //validate existing type name
        validateNameIsExists(typeInputDto.getName());

        log.info("saving type");
        typeRepository.save(getTypeFromDto(typeInputDto));
        log.info("saved type");
        return new TypeResponse(MessageConstants.TYPE_SAVED, true);
    }

    private void validateNameIsExists(final String name) {
        final Boolean isExist = typeRepository.existByName(name);
        if(isExist){
            throw new AssetManagementException(new ErrorResponse(
                    ErrorEnum.EXISTING_TYPE_NAME.getErrorCode(), ErrorEnum.EXISTING_TYPE_NAME.getErrorMessage(), false
            ));
        }
    }

    private Type getTypeFromDto(final TypeInputDto typeInputDto) {
        final Type type = new Type();
        type.setName(typeInputDto.getName());
        type.setIsActive(true);

        return type;
    }

    private Boolean validateTypeName(final String name) {
        try {
            Integer.parseInt(name);
        }catch (final NumberFormatException exception){
            log.error("Invalid type name exception: ", exception);
            return true;
        }
        return false;
    }
}
