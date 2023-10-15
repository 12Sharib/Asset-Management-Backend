package com.assetmanagement.exceptions;

import com.assetmanagement.response.ErrorResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Log4j2
public class GlobalExceptions {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(final MethodArgumentNotValidException exception) {
        log.error("validation exceptions: ", exception);
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(new ErrorResponse(false, errors), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(AssetManagementException.class)
    public ResponseEntity<ErrorResponse> handleAssetManagementExceptions(final AssetManagementException assetManagementException){
        log.error("Asset Management Exception {0}: ", assetManagementException);
        return new ResponseEntity<>(assetManagementException.getErrorResponse(), HttpStatus.BAD_REQUEST);
    }
}
