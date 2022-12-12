/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polizas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author jhonatan.mendez
 */
@AllArgsConstructor
@NoArgsConstructor
@Data public class EmpleadoDTO extends EntityBaseDTO {
    
    private Long empleado_id;
    
    private String nombre;
    
    private String apellido;
    
    private String puesto;
    
}
