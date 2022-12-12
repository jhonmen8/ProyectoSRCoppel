/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polizas.dto;
import java.util.Date;
import javax.persistence.MappedSuperclass;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author jhonatan.mendez
 */

@MappedSuperclass
@NoArgsConstructor
@Data public class EntityBaseDTO {
    
    private int estatus;
    
    private Date fecInserta;
    
    private Date fecActualiza;
    
    private Long idu_usuarioInserta;
    
    private Long idu_usuarioActualiza;  
}
