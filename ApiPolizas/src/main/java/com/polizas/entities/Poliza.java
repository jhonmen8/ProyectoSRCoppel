/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polizas.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author jhonatan.mendez
 */

@Entity
@Table(name = "polizas")
@XmlRootElement
@AllArgsConstructor
@NoArgsConstructor
@Data public class Poliza extends EntityBase implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "poliza_id")
    private Long poliza_id;  
    
    @Basic(optional = false)
    @NotNull    
    @Column(name = "cantidad")
    private int cantidad;
    
       
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idu_poliza", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<PolizaDetalle> polizaDetalle;
    
}
