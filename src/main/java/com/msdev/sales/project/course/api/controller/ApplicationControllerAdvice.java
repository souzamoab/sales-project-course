package com.msdev.sales.project.course.api.controller;

import com.msdev.sales.project.course.domain.errors.ApiErrors;
import com.msdev.sales.project.course.exception.BusinessRuleException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(BusinessRuleException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleBusinessRuleException(BusinessRuleException businessRuleException) {
        String errorMessage = businessRuleException.getMessage();
        return new ApiErrors(errorMessage);
    }

}
