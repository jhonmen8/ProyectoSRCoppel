/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polizas.servicesImp;

import com.polizas.dto.EmpleadoDTO;
import com.polizas.entities.Empleado;
import com.polizas.repositories.EmpleadoRepository;
import com.polizas.services.EmpleadoService;
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
public class EmpleadoServiceImp implements  EmpleadoService {
    
    @Autowired
    private EmpleadoRepository repository;

    @Override
    public EmpleadoDTO consultarEmpleadoByID(Long id) {
        Optional<Empleado> empleado = repository.findById(id);
        if(empleado.isPresent()){
            return new ModelMapper().map(empleado.get(),EmpleadoDTO.class);    
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NO ENCONTRADO");
        }
    }
    
    @Override
    public List<EmpleadoDTO> consultarEmpleados() {
        
        List<EmpleadoDTO> empleadoList = new ArrayList<>();
        repository.findAll().forEach(item -> {
            empleadoList.add(new ModelMapper().map(item,EmpleadoDTO.class));
        });
        return empleadoList;
    }
    
}
