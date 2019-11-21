/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.celk.ws.utils;

import javax.ws.rs.core.Response;

/**
 *
 * @author kelvin
 */
public class Controller {
    
    private final FeedBuilderInterface feedBuilder = new FeedBuilder();

    public Controller() {
    }

    protected void addData(Object obj) {
        feedBuilder.add(obj);
    }

    protected void addData(String key, Object obj) {
        feedBuilder.add(key, obj);
    }

    protected void addError(Exception exception) {
        feedBuilder.add(new MensagemDto(MensagemTipo.ERROR.toString(), exception.getLocalizedMessage()));
    }
    
    protected void addSuccess(String mensagem) {
        feedBuilder.add(new MensagemDto(MensagemTipo.SUCCESS.toString(), mensagem));
    }

    protected Response build() {
        return feedBuilder.build();
    }

    protected Response build(String mediaType) {
        return feedBuilder.build(mediaType);
    }    
}
