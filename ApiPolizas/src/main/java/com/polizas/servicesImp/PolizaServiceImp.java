/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polizas.servicesImp;

import com.polizas.dto.PolizaDTO;
import com.polizas.entities.Inventario;
import com.polizas.entities.Poliza;
import com.polizas.entities.PolizaDetalle;
import com.polizas.repositories.InventarioRepository;
import com.polizas.repositories.PolizaRepository;
import com.polizas.services.PolizaService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.modelmapper.ModelMapper;
/**
 *
 * @author jhonatan.mendez
 */
@Service
public class PolizaServiceImp implements PolizaService{
    
    private static final Logger LOGGER = LogManager.getLogger(PolizaServiceImp.class.getName());
    
    @Autowired
    private PolizaRepository repository;
    
    @Autowired
    private InventarioRepository repositoryInv;


    @Override
    public PolizaDTO consultarPolizaByID(Long id) {
        Optional<Poliza> poliza = repository.findById(id);
        LOGGER.log(Level.FATAL, HttpStatus.NOT_FOUND);
        if(poliza.isPresent()){
            return new ModelMapper().map(poliza.get(), PolizaDTO.class);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NO ENCONTRADO");
        }
    }

    @Override
    public List<PolizaDTO> consultarPolizas() {        
        List<PolizaDTO> polizas =  new ArrayList<>();        
        repository.findAll().forEach(item->{
            polizas.add(new ModelMapper().map(item, PolizaDTO.class));
        });        
        return polizas;
    }

    @Override
    public PolizaDTO guardarPoliza(PolizaDTO poliza) {        
        Poliza polizaSave = new ModelMapper().map(poliza, Poliza.class);
        if(polizaSave.getPoliza_id() == Long.parseLong("0")){
            polizaSave.setIdu_usuarioInserta(Long.parseLong("0"));
            polizaSave.setIdu_usuarioActualiza(Long.parseLong("0"));
            polizaSave.setFecInserta(new Date());
            polizaSave.setFecActualiza(new Date());
            
            polizaSave.getPolizaDetalle().forEach(det -> {
                det.setIdu_usuarioInserta(Long.parseLong("0"));
                det.setIdu_usuarioActualiza(Long.parseLong("0"));
                det.setFecInserta(new Date());
                det.setFecActualiza(new Date());
            });
            
            polizaSave = repository.save(polizaSave);            
            //Descontamos el inventario y en caso de no alcanzar lanzamos una excepción
            polizaSave.getPolizaDetalle().forEach(det -> {                
                Long id = det.getSku_id().getSku_id();                
                Optional<Inventario> inv = repositoryInv.findById(id);
                if(inv.isPresent()){
                    int cantidadDescontar = det.getCantidad();
                    int cantidad = inv.get().getCantidad();
                    cantidad -= cantidadDescontar;
                    if(cantidad < 0){
                        throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "CANTIDAD DEL ARTICULO: "+inv.get().getNombre()+" INSUFICIENTE");
                    }
                    inv.get().setCantidad(cantidad);
                    repositoryInv.save(inv.get());
                }
            });
        }     
        
        return new ModelMapper().map(polizaSave, PolizaDTO.class);
    }

    @Override
    public PolizaDTO eliminarPoliza(Long id) {
        Optional <Poliza> poliza = this.repository.findById(id);
        if(poliza.isPresent()){            
            PolizaDTO rPoliza = new ModelMapper().map(poliza.get(), PolizaDTO.class);            
            this.repository.delete(poliza.get());
            return rPoliza;
            
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NO ENCONTRADO");
        }
    }

    @Override
    public PolizaDTO guardarPoliza(Long id, PolizaDTO poliza) {
        Optional <Poliza> existePoliza = this.repository.findById(id);
        if(existePoliza.isPresent()){  
            
            Poliza polizaAct = existePoliza.get();
            polizaAct.setCantidad(poliza.getCantidad());
            polizaAct.setIdu_usuarioActualiza(Long.parseLong("0"));            
            polizaAct.setFecActualiza(new Date());
            
            List<PolizaDetalle> polizaDetalle = new ArrayList<>();
            
            
            poliza.getPolizaDetalle().forEach(det -> {
                if(det.getPolizadetalle_id() == Long.parseLong("0")){
                    det.setIdu_usuarioInserta(Long.parseLong("0"));
                    det.setIdu_usuarioActualiza(Long.parseLong("0"));
                    det.setFecInserta(new Date());
                    det.setFecActualiza(new Date());
                }else{                    
                    det.setIdu_usuarioActualiza(Long.parseLong("0"));                    
                    det.setFecActualiza(new Date());
                }                
                polizaDetalle.add(new ModelMapper().map(det, PolizaDetalle.class));                
            });
            polizaAct.setPolizaDetalle(polizaDetalle);
            this.repository.save(polizaAct);
            return new ModelMapper().map(polizaAct, PolizaDTO.class);
            
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NO ENCONTRADO");
        }
    }
    
}
