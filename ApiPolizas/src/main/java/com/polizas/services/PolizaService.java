/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polizas.services;

import com.polizas.dto.PolizaDTO;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author jhonatan.mendez
 */
@Service
public interface PolizaService {    
    public PolizaDTO consultarPolizaByID(Long id);
    public List<PolizaDTO> consultarPolizas();
    public PolizaDTO guardarPoliza(PolizaDTO poliza);
    public PolizaDTO guardarPoliza(Long id, PolizaDTO poliza);
    public PolizaDTO eliminarPoliza(Long id);
}
