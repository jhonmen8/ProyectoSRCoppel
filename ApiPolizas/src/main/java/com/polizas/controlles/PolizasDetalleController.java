/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polizas.controlles;

import com.polizas.services.PolizaDetalleService;
import com.polizas.utils.ApiResponse;
import com.polizas.utils.Meta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jhonatan.mendez
 */
@RestController
public class PolizasDetalleController {
    
    @Autowired
    private PolizaDetalleService service;
    
    
    @DeleteMapping(value = "polizasdetalle/{id:[\\d]+}")
    public ResponseEntity<Object> eliminarPolizas(@PathVariable Long id )  {
        HttpHeaders httpHeaders =  new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return new ResponseEntity<>(new ApiResponse(new Meta("OK", HttpStatus.OK.value() , ""), service.eliminarPolizaDetalle(id)), httpHeaders, HttpStatus.OK);
    }
}
