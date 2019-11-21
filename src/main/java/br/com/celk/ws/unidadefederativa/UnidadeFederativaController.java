/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.celk.ws.unidadefederativa;

import br.com.celk.ws.utils.Controller;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 *
 * @author kelvin
 */
@Stateless
@Path("uf")
public class UnidadeFederativaController extends Controller {    
    private final UnidadeFederativaServicoEjb servico;

    public UnidadeFederativaController() {
        this.servico = new UnidadeFederativaServicoEjb();
    }

    @POST
    @Consumes({"application/xml", "application/json"})
    @Produces({"application/json"})
    public Response create(UnidadeFederativaCommand input) {
        try {
            UnidadeFederativaDto dto = UnidadeFederativaDto.builder()
                    .nome(input.getNome())
                    .sigla(input.getSigla())
                    .build();
            
            servico.create(dto);
            addSuccess("Sucesso ao registrar a unidade federativa.");
        } catch (Exception ex) {
            addError(ex);
        }
        
        return build();
    }
    
    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    @Produces({"application/json"})
    public Response edit(@PathParam("id") Integer id, UnidadeFederativaCommand input) {
        try {
            UnidadeFederativaDto dto = UnidadeFederativaDto.builder()
                    .id(id)
                    .nome(input.getNome())
                    .sigla(input.getSigla())
                    .build();
            
            servico.update(dto);
            addSuccess("Sucesso ao atualizar a unidade federativa.");
        } catch (Exception ex) {
            addError(ex);
        }
        
        return build();
    }

    @DELETE
    @Path("{id}")
    @Produces({"application/json"})
    public Response remove(@PathParam("id") Integer id) {
        try {
            servico.delete(id);
            addSuccess("Sucesso ao remover a unidade federativa.");
        } catch (Exception ex) {
            addError(ex);
        }
        
        return build();
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Response find(@PathParam("id") Integer id) {
        try {
            addData(servico.get(id));
        } catch (Exception ex) {
            addError(ex);
        }
        
        return build();
    }

    @GET
    @Produces({"application/xml", "application/json"})
    public Response findAll() {
        try {
            addData(servico.get());
        } catch (Exception ex) {
            addError(ex);
        }
        
        return build();
    }
    
}
