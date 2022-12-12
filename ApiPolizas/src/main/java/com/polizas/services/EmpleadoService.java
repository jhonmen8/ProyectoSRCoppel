/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polizas.services;

import com.polizas.dto.EmpleadoDTO;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author jhonatan.mendez
 */
@Service
public interface EmpleadoService {
    public EmpleadoDTO consultarEmpleadoByID(Long id);
    public List<EmpleadoDTO> consultarEmpleados();
}
