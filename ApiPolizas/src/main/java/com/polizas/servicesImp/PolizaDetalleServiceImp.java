/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polizas.servicesImp;

import com.polizas.dto.PolizaDetalleDTO;
import com.polizas.entities.Inventario;
import com.polizas.entities.Poliza;
import com.polizas.entities.PolizaDetalle;
import com.polizas.repositories.InventarioRepository;
import com.polizas.repositories.PolizaDetalleRepository;
//import com.polizas.repositories.PolizaDetalleRepository;
import com.polizas.repositories.PolizaRepository;
import com.polizas.services.PolizaDetalleService;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
public class PolizaDetalleServiceImp implements PolizaDetalleService {
    
    private static final Logger LOGGER = LogManager.getLogger(PolizaDetalleServiceImp.class.getName());
    
    @Autowired
    private PolizaDetalleRepository repositoryDet;
    
    @Autowired
    private PolizaRepository repositoryPol;
    
    @Autowired
    private InventarioRepository repositoryInv;
    
    @Override
    public PolizaDetalleDTO eliminarPolizaDetalle(Long id) {        
        Optional<PolizaDetalle> polizaDetalle = repositoryDet.findById(id);        
        if(polizaDetalle.isPresent()){
            PolizaDetalleDTO polizaDetalleDTO = new ModelMapper().map(polizaDetalle.get() ,PolizaDetalleDTO.class);            
            Optional<Poliza> poliza = repositoryPol.findById(polizaDetalleDTO.getIdu_poliza().getPoliza_id());
            if(poliza.isPresent()){
                
                //Regresamos al inventario la cantidad que estamos eliminando del detalle
                Long idInv = polizaDetalleDTO.getSku_id().getSku_id();                
                Optional<Inventario> inv = repositoryInv.findById(idInv);
                if(inv.isPresent()){
                    int cantidadSumar = polizaDetalleDTO.getCantidad();
                    int cantidad = inv.get().getCantidad();
                    cantidad += cantidadSumar;
                    inv.get().setCantidad(cantidad);
                    repositoryInv.save(inv.get());
                }
                
                //Eliminamos el detalle de la poliza
                Poliza polDel = poliza.get();
                int t = polDel.getPolizaDetalle().size();
                polDel.getPolizaDetalle().remove(polizaDetalle.get());
                t = polDel.getPolizaDetalle().size();
                this.repositoryDet.deleteById(id);
                this.repositoryPol.save(polDel);    
            }            
            return polizaDetalleDTO;
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NO ENCONTRADO");
        }
    }
    
}
