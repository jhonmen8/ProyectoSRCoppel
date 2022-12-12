/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polizas.controlles;

import com.polizas.dto.PolizaDTO;
import com.polizas.services.PolizaService;
import com.polizas.utils.ApiResponse;
import com.polizas.utils.Meta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jhonatan.mendez
 */
@RestController
public class PolizasController {
    
    @Autowired    
    private PolizaService polizaService;
    
    @GetMapping(value={"/polizas", "polizas/{id:[\\d]+}"})
    public ResponseEntity<Object> consultarPolizas(@PathVariable(name = "id", required = false) Long id)  {        
        HttpHeaders httpHeaders =  new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);       
        if (id == null)
            return new ResponseEntity<>(new ApiResponse(new Meta("OK", HttpStatus.OK.value() , ""), polizaService.consultarPolizas()), httpHeaders, HttpStatus.OK);
        else
            return new ResponseEntity<>(new ApiResponse(new Meta("OK", HttpStatus.OK.value() , ""), polizaService.consultarPolizaByID(id)), httpHeaders, HttpStatus.OK);    
    }
    
    
    @PostMapping(value="polizas", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> guardarPolizas( @RequestBody PolizaDTO poliza )  {
        HttpHeaders httpHeaders =  new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        
        return new ResponseEntity<>(new ApiResponse(new Meta("OK", HttpStatus.OK.value() , ""), polizaService.guardarPoliza(poliza)), httpHeaders, HttpStatus.OK);
    }
    
    @DeleteMapping(value = "polizas/{id:[\\d]+}")
    public ResponseEntity<Object> eliminarPolizas(@PathVariable Long id )  {
        HttpHeaders httpHeaders =  new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);            
        return new ResponseEntity<>(new ApiResponse(new Meta("OK", HttpStatus.OK.value() , ""), polizaService.eliminarPoliza(id)), httpHeaders, HttpStatus.OK);
    }
    
    @PutMapping(value="polizas/{id:[\\d]+}")
    public ResponseEntity<Object> actualizarPolizas(@PathVariable Long id, @RequestBody PolizaDTO poliza ){
        HttpHeaders httpHeaders =  new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);            
        return new ResponseEntity<>(new ApiResponse(new Meta("OK", HttpStatus.OK.value() , ""), polizaService.guardarPoliza(id, poliza)), httpHeaders, HttpStatus.OK);
    }
}
