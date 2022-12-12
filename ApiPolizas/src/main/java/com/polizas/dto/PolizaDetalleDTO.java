/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polizas.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author jhonatan.mendez
 */
@AllArgsConstructor
@NoArgsConstructor
@Data public class PolizaDetalleDTO extends EntityBaseDTO {
    
    private Long polizadetalle_id;
    
    private int cantidad;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonBackReference
    private PolizaDTO idu_poliza;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)    
    private InventarioDTO sku_id;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)    
    private EmpleadoDTO empleado_id;
    
    
    
}
