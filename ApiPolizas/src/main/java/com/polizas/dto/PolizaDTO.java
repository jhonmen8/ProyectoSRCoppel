/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polizas.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author jhonatan.mendez
 */
@AllArgsConstructor
@NoArgsConstructor
@Data public class PolizaDTO extends EntityBaseDTO{
    
    private Long poliza_id;  
    
    private int cantidad;
    
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    @JsonManagedReference
    private List<PolizaDetalleDTO> polizaDetalle;
}
