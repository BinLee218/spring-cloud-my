package com.company.mybatis.aop;

import com.company.mybatis.commons.ApiResponse;
import com.company.mybatis.commons.adminException.AdminException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * @author bin.li
 * @date 2020/5/5
 */
@Slf4j
@RestControllerAdvice(basePackages = "com.company.mybatis.controller")
public class GlobalExceptionHandler {

    /**
     * 捕获全局异常，处理所有不可知的异常
     */
    @ExceptionHandler(value=Exception.class)
    public Object handleException(Exception e, HttpServletRequest request) {
        log.error("AvatecExceptionHandler:{},url:{}", e.getMessage(), request.getRequestURL());
        ApiResponse badRequest = new ApiResponse();
        badRequest.setSubCode(HttpStatus.BAD_REQUEST.value());
        badRequest.setSubMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
        log.error(e.getMessage(), e);
        return badRequest;
    }

    /**
     * 捕获自定义异常，处理所有不可知的异常
     */
    @ExceptionHandler(value= AdminException.class)
    public Object AdminException(Exception e, HttpServletRequest request) {
        AdminException adminException = (AdminException)e;
        log.error("AdminException: [{}]-[{}], url: [{}]", adminException.getLabel(), adminException.getMessage(), request.getRequestURL());
        ApiResponse badRequest = new ApiResponse();
        badRequest.setSubCode(adminException.getStatus());
        badRequest.setSubMessage(String.format("%s",  adminException.getMessage()));
        log.error(e.getMessage(), e);
        return badRequest;
    }


    /**
     * 捕获参数校验异常
     * @param req
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = BindException.class)
    public ApiResponse NotValidExceptionHandler(HttpServletRequest req, BindException e) throws Exception {
        log.error("AvatecExceptionHandler:{},url:{}", e.getMessage(), req.getRequestURL());
        ApiResponse badRequest = new ApiResponse();
        badRequest.setSubCode(HttpStatus.BAD_REQUEST.value());
        BindingResult bindingResult = e.getBindingResult();
        errorMessage(bindingResult, badRequest);
        log.error(e.getMessage(), e);
        return badRequest;
    }

    private void errorMessage(BindingResult bindingResult, ApiResponse badRequest) {
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if (CollectionUtils.isNotEmpty(fieldErrors)) {
            FieldError fieldError = fieldErrors.get(0);
            String field = fieldError.getField();
            badRequest.setSubMessage(String.format("%s, Please enter the correct data", field));
        } else {
            badRequest.setSubMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
        }
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ApiResponse methodArgumentNotValiExceptionHandler(HttpServletRequest req, MethodArgumentNotValidException e) throws Exception {
        log.error("methodArgumentNotValiExceptionHandler:{},url:{}", e.getMessage(), req.getRequestURL());
        ApiResponse badRequest = new ApiResponse();
        badRequest.setSubCode(HttpStatus.BAD_REQUEST.value());
        BindingResult bindingResult = e.getBindingResult();
        errorMessage(bindingResult, badRequest);
        log.error(e.getMessage(), e);
        return badRequest;
    }

    /**
     * 捕获DateTimeParseException
     */
    @ExceptionHandler(value= DateTimeParseException.class)
    public Object dateTimeParseException(Exception e, HttpServletRequest request) {
        log.error("AvatecExceptionHandler:{},url:{}", e.getMessage(), request.getRequestURL());
        ApiResponse badRequest = new ApiResponse();
        badRequest.setSubCode(HttpStatus.BAD_REQUEST.value());
        DateTimeParseException e1 = (DateTimeParseException) e;
        badRequest.setSubMessage(String.format("%s, Please enter a time type",  e1.getParsedString()));
        log.error(e.getMessage(), e);
        return badRequest;
    }
}
