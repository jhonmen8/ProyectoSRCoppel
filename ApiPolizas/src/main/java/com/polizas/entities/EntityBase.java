/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polizas.entities;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author jhonatan.mendez
 */
@MappedSuperclass
@NoArgsConstructor
@Data public class EntityBase  {
    @Basic(optional = false)
    @NotNull
    @Column(name = "estatus")
    private int estatus;
    
    @Column(name = "fec_inserta", updatable = false)
    private Date fecInserta;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "fec_actualiza")
    private Date fecActualiza;
    
    @Column(name = "idu_usuario_inserta", updatable = false)
    private Long idu_usuarioInserta;
    
    @Column(name = "idu_usuario_actualiza")
    private Long idu_usuarioActualiza;  
    
}
