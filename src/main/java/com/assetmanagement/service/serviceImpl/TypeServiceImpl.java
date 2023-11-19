package com.assetmanagement.service.serviceImpl;

import com.assetmanagement.constants.MessageConstants;
import com.assetmanagement.convertor.EntityDtoConvertor;
import com.assetmanagement.dao.TypeRepository;
import com.assetmanagement.dto.TypeDto;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeRepository typeRepository;
    @Autowired
    private EntityDtoConvertor entityDtoConvertor;
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
        validateTypeNameIsExists(typeInputDto.getName());

        log.info("saving type");
        typeRepository.save(getTypeFromDto(typeInputDto));
        log.info("saved type");

        log.info("Completed add type service");
        return new TypeResponse(MessageConstants.TYPE_SAVED, true);
    }

    @Override
    public TypeResponse delete(final Integer typeId) {
        log.info("Started deleting a type service");
        //validate type id
        validateTypeId(typeId);

        //Get type
        final Optional<Type> optionalType = typeRepository.findById(typeId);
        Type type = new Type();
        if (optionalType.isPresent()){
            type = optionalType.get();
        }
        //Update type status as in active
        type.setIsActive(false);

        //save an updated type
        typeRepository.save(type);

        log.info("Completed deleting a type service");
        return null;
    }

    @Override
    public TypeResponse getAllActiveTypes() {
        log.info("Started a service of get all active types");

        final List<Type> types = typeRepository.findAllActiveTypes();
        final List<TypeDto> typeDtos = new ArrayList<>();

        types.forEach(type -> {
            typeDtos.add(entityDtoConvertor.convertTypeToDto(type));
        });

        log.info("Completed a service of get all active types");
        return new TypeResponse(true, typeDtos);
    }

    @Override
    public TypeResponse updateDetails(final TypeInputDto typeInputDto, final Integer typeId) {
        log.info("Started updating type details service");
        //validate type id
        validateTypeId(typeId);

        //validate type name
        validateTypeName(typeInputDto.getName());

        //validate existing type name
        validateTypeName(typeInputDto.getName());

        final Optional<Type> optionalPreviousType = typeRepository.findById(typeId);
        Type previousType = new Type();

        if (optionalPreviousType.isPresent()){
            previousType = optionalPreviousType.get();
        }
        previousType.setName(previousType.getName());

        log.info("Saving type");
        typeRepository.save(getTypeFromDto(typeInputDto));
        log.info("Saved type");

        log.info("Completed updating type details service");
        return null;
    }

    private void validateTypeId(final Integer typeId) {
        if(!typeRepository.existsById(typeId)){
            throw new AssetManagementException(new ErrorResponse(
                    ErrorEnum.INVALID_TYPE_ID.getErrorCode(), ErrorEnum.INVALID_TYPE_ID.getErrorMessage(), false
            ));
        }
    }

    private void validateTypeNameIsExists(final String name) {
        final Boolean isExist = typeRepository.existByName(name);
        if(isExist != null){
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
