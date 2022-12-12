/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polizas.controlles;

import com.polizas.services.EmpleadoService;
import com.polizas.utils.ApiResponse;
import com.polizas.utils.Meta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jhonatan.mendez
 */
@RestController
public class EmpleadosController {
    
    @Autowired
    private EmpleadoService empleadoService;
            
    @GetMapping(value={"/empleados", "empleados/{id:[\\d]+}"})
    public ResponseEntity<Object> consultarEmpleados(@PathVariable(name = "id", required = false) Long id)  {        
        HttpHeaders httpHeaders =  new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);       
        if (id == null)
            return new ResponseEntity<>(new ApiResponse(new Meta("OK", HttpStatus.OK.value() , ""), empleadoService.consultarEmpleados()), httpHeaders, HttpStatus.OK);
        else
            return new ResponseEntity<>(new ApiResponse(new Meta("OK", HttpStatus.OK.value() , ""), empleadoService.consultarEmpleadoByID(id)), httpHeaders, HttpStatus.OK);    
    }
}
