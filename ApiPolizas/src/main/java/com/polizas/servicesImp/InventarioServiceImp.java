/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polizas.servicesImp;

import com.polizas.dto.InventarioDTO;
import com.polizas.entities.Inventario;
import com.polizas.repositories.InventarioRepository;
import com.polizas.services.InventarioService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author jhonatan.mendez
 */
@Service
public class InventarioServiceImp implements InventarioService {
    
    @Autowired
    private InventarioRepository repository;

    @Override
    public InventarioDTO consultarInventarioByID(Long id) {
         Optional<Inventario> inventario = repository.findById(id);
        if(inventario.isPresent()){
            return new ModelMapper().map(inventario.get(),InventarioDTO.class);
            
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NO ENCONTRADO");
        }
    }

    @Override
    public List<InventarioDTO> consultarInventario() {
        List<InventarioDTO> inventarioList = new ArrayList<>();
        repository.findAll().forEach(item->{
            inventarioList.add(new ModelMapper().map(item,InventarioDTO.class));
        });
        
        return inventarioList;
    }
    
}
