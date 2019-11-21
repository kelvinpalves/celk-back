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
public class MensagemDto {
    
    private String tipo;
    private String mensagem;

    public MensagemDto(String tipo, String mensagem) {
        this.tipo = tipo;
        this.mensagem = mensagem;
    }
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    
}
