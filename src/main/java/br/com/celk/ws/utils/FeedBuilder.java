/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.celk.ws.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

public class FeedBuilder implements FeedBuilderInterface {

    private static final String MESSAGE_TYPE = "message";
    private static final String DATA_TYPE = "data";
    private static final String STATUS_TYPE = "status";

    private String status;
    private final Map<String, Object> feeds;
    private final Collection<MensagemDto> messages;

    public FeedBuilder() {
        feeds = new HashMap<>();
        messages = new ArrayList<>();
        status = "true";
    }

    @Override
    public void add(String key, Object obj) {
        feeds.put(key, obj);
    }

    @Override
    public void add(Object obj) {
        this.add(obj.getClass().getSimpleName(), obj);
    }

    @Override
    public void add(MensagemDto obj) {
        if ("error".equals(obj.getTipo())) {
            status = "false";
        }
        messages.add(obj);
    }

    private ResponseBuilder simpleBuild() {
        Map<String, Object> build = new HashMap<>();
        build.put(DATA_TYPE, feeds);
        build.put(MESSAGE_TYPE, messages);
        build.put(STATUS_TYPE, status);
        
        return Response.ok(build);
    }

    @Override
    public Response build() {
        return simpleBuild().build();
    }

    @Override
    public Response build(String mediaType) {
        return simpleBuild().type(mediaType).build();
    }

}
