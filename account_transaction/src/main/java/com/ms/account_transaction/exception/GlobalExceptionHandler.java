package com.ms.account_transaction.exception;

import com.ms.account_transaction.dto.response.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.WebRequest;
import com.ms.account_transaction.common.ResponseUtil;

/**
 * @author : Freddy Torres
 * file :  GlobalExceptionHandler
 * @since : 11/3/2025, mar
 **/

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> notValid(MethodArgumentNotValidException ex, WebRequest request) {
        return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), request);
    }
    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ErrorResponse> handleClientNotFoundException(GeneralException ex, WebRequest request) {
        return ResponseUtil.createErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), request);
    }
}
