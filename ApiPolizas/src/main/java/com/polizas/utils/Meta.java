/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polizas.utils;

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
@Data public class Meta {
    private String status;
    private int codigo;
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String message;
}
