/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.celk.ws.unidadefederativa;

import lombok.Builder;
import lombok.Data;

/**
 *
 * @author kelvin
 */

@Data
@Builder
public class UnidadeFederativaDto {
    private final Integer id;
    private final String nome;
    private final String sigla;
}
