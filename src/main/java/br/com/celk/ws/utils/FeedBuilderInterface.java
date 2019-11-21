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
import javax.ws.rs.core.Response;

/**
 *
 * @author cgoettert
 */
public interface FeedBuilderInterface {

    public void add(String key, Object obj);

    public void add(Object obj);

    public void add(MensagemDto obj);

    public Response build();

    public Response build(String mediaType);
}
