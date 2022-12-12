/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polizas.repositories;


import com.polizas.entities.Poliza;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jhonatan.mendez
 */
public interface PolizaRepository extends JpaRepository< Poliza, Long> {
    
}
