package com.mycompany.projectmanagement.execption;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorModel>> handleFieldValidation(MethodArgumentNotValidException manv){
        List<ErrorModel> errorModelList = new ArrayList<>();
        ErrorModel errorModel = null;
        List<FieldError> fieldErrorsList = manv.getBindingResult().getFieldErrors();

        for(FieldError fe : fieldErrorsList){
            logger.info("Inside field validation {} - {}",fe.getField(),fe.getDefaultMessage());
            logger.debug("Inside field validation {} - {}",fe.getField(),fe.getDefaultMessage());
            errorModel = new ErrorModel();
            errorModel.setCode(fe.getField());
            errorModel.setMessage(fe.getDefaultMessage());
            errorModelList.add(errorModel);
        }
        return new ResponseEntity<List<ErrorModel>>(errorModelList,HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<List<ErrorModel>> handlerBusinessException(BusinessException businessException){
//        System.out.println("BusinessException is thrown");
        for(ErrorModel eM : businessException.getErrors()){
            logger.info("Inside Business Exception {} - {}",eM.getCode(),eM.getMessage());
            logger.debug("Inside Business Exception {} - {}",eM.getCode(),eM.getMessage());
            logger.warn("Inside Business Exception {} - {}",eM.getCode(),eM.getMessage());
            logger.error("Inside Business Exception {} - {}",eM.getCode(),eM.getMessage());

        }
        return new ResponseEntity<List<ErrorModel>>(businessException.getErrors(), HttpStatus.BAD_REQUEST);
    }
}
