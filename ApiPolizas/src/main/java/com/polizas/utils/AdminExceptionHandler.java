/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polizas.utils;

import java.time.LocalDateTime;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author jhonatan.mendez
 */
@ControllerAdvice
public class AdminExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(value = {ResponseStatusException.class})
    public ResponseEntity<Object> handleResponseStatusException(ResponseStatusException ex) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);        
        return new ResponseEntity<>(new ApiResponse(new Meta("CLIENT_ERROR", ex.getStatus().value(), ex.getReason()), null), httpHeaders, ex.getStatus());
    }
    
    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Object> handleException(RuntimeException runtimeException, WebRequest webRequest) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return handleExceptionInternal(
            runtimeException,
            new ApiResponse(new Meta("SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR.value(), (runtimeException.getMessage() == null ? runtimeException.getClass().toString() : runtimeException.getMessage())), null),
            httpHeaders,
            HttpStatus.INTERNAL_SERVER_ERROR,
            webRequest
        );
    }
    
}
