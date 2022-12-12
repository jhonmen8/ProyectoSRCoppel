/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polizas.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author jhonatan.mendez
 */
@Entity
@Table(name = "polizas_detalle")
@AllArgsConstructor
@NoArgsConstructor
@Data public class PolizaDetalle extends EntityBase implements Serializable {
    
     private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "polizadetalle_id")
    private Long polizadetalle_id;
    
    @JoinColumn(name = "idu_poliza", referencedColumnName = "poliza_id")
    @ManyToOne(optional = false)
    @JsonBackReference
    private Poliza idu_poliza;
    
    
    @JoinColumn(name = "empleado_id", referencedColumnName = "empleado_id")
    @ManyToOne(optional = false) 
    private Empleado empleado_id;
    
    
    @JoinColumn(name = "sku_id", referencedColumnName = "sku_id")
    @ManyToOne(optional = false) 
    private Inventario sku_id;
    
    @Basic(optional = false)
    @NotNull    
    @Column(name = "cantidad")
    private int cantidad;
    
}
