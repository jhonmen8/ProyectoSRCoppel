/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polizas.controlles;

import com.polizas.services.InventarioService;
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
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author jhonatan.mendez
 */
@RestController
public class InventariosController {
    
    @Autowired
    private InventarioService inventarioService;
    private static final Logger LOGGER = LogManager.getLogger(InventariosController.class.getName());

    @GetMapping(value={"/inventarios", "inventarios/{id:[\\d]+}"})
    public ResponseEntity<Object> consultarInventario(@PathVariable(name = "id", required = false) Long id)  {        
        HttpHeaders httpHeaders =  new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);       
        LOGGER.log(Level.INFO, "consultarInventario");

        if (id == null)
            return new ResponseEntity<>(new ApiResponse(new Meta("OK", HttpStatus.OK.value() , ""), inventarioService.consultarInventario()), httpHeaders, HttpStatus.OK);
        else
            return new ResponseEntity<>(new ApiResponse(new Meta("OK", HttpStatus.OK.value() , ""), inventarioService.consultarInventarioByID(id)), httpHeaders, HttpStatus.OK);    
    }
    
}
