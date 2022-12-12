/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polizas.services;

import com.polizas.dto.InventarioDTO;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author jhonatan.mendez
 */
@Service
public interface InventarioService {
    public InventarioDTO consultarInventarioByID(Long id);
    public List<InventarioDTO> consultarInventario();
}
