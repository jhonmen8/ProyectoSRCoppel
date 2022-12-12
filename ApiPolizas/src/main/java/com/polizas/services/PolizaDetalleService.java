/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polizas.services;

import com.polizas.dto.PolizaDetalleDTO;
import org.springframework.stereotype.Service;

/**
 *
 * @author jhonatan.mendez
 */
@Service
public interface PolizaDetalleService {
    public PolizaDetalleDTO eliminarPolizaDetalle(Long id);
}
