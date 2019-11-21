/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.celk.ws.utils;

/**
 *
 * @author kelvin
 */
public enum MensagemTipo {

    SUCCESS,
    ERROR,
    WARNING,
    INFO;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
        
}
