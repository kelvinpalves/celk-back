/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.celk.ws.unidadefederativa;

import br.com.celk.model.UnidadeFederativa;
import br.com.celk.service.DAO1;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author kelvin
 */

public class UnidadeFederativaServicoEjb {
    
    private final UnidadeFederativaDaoJpa service = new UnidadeFederativaDaoJpa(false);
    
    public UnidadeFederativaServicoEjb() {
    }

    public void create(UnidadeFederativaDto dto) throws Exception {
        validarEntrada(dto);
        
        try {   
            UnidadeFederativa model = new UnidadeFederativa();
            model.setNome(dto.getNome());
            model.setSigla(dto.getSigla());
            model.setDataHoraAtualizacao(new Date());
            
            DAO1.begin();
            DAO1.getEM().persist(model);
            DAO1.commit();
        } catch (Exception ex) {
            DAO1.rollback();
            throw ex;
        }
    }
    
    public UnidadeFederativaDto get(Integer id) throws Exception {
        try {   
            UnidadeFederativa model = DAO1.getEM().find(UnidadeFederativa.class, id);
            
            if (model == null) {
                throw new Exception("A unidade federativa não foi encontrada na base de dados.");
            }
            
            UnidadeFederativaDto dto = UnidadeFederativaDto.builder()
                    .id(model.getIdUnidadeFederativa())
                    .nome(model.getNome())
                    .sigla(model.getSigla())
                    .build();
            
            return dto;
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    public List<UnidadeFederativaDto> get() throws Exception {
        try {   
            List<UnidadeFederativa> lista = service.findAll();
            List<UnidadeFederativaDto> listaResultante = new ArrayList<>();
            
            for (UnidadeFederativa model : lista) {
                UnidadeFederativaDto dto = UnidadeFederativaDto.builder()
                    .id(model.getIdUnidadeFederativa())
                    .nome(model.getNome())
                    .sigla(model.getSigla())
                    .build();
                
                listaResultante.add(dto);
            }
            
            return listaResultante;
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    public void delete(Integer id) throws Exception {
        try {   
            UnidadeFederativa model = DAO1.getEM().find(UnidadeFederativa.class, id);
            
            if (model == null) {
                throw new Exception("A unidade federativa não foi encontrada na base de dados.");
            }
            
            DAO1.begin();
            DAO1.getEM().remove(model);
            DAO1.commit();
        } catch (Exception ex) {
            DAO1.rollback();
            throw ex;
        }
    }
    
    public void update(UnidadeFederativaDto dto) throws Exception {
        validarEntrada(dto);
        
        try {   
            
            if (dto.getId() == null) {
                throw new Exception("O identificador da unidade federativa não foi informado.");
            }
            
            UnidadeFederativa model = DAO1.getEM().find(UnidadeFederativa.class, dto.getId());
            
            if (model == null) {
                throw new Exception("A unidade federativa não foi encontrada na base de dados.");
            }
            
            model.setNome(dto.getNome());
            model.setSigla(dto.getSigla());
            model.setDataHoraAtualizacao(new Date());
            
            DAO1.begin();
            DAO1.getEM().persist(model);
            DAO1.commit();
        } catch (Exception ex) {
            DAO1.rollback();
            throw ex;
        }
    }
    
    public void validarEntrada(UnidadeFederativaDto dto) throws Exception {
        if (StringUtils.isBlank(dto.getNome())) {
            throw new Exception("O campo nome é obrigatório.");
        }
        
        if (StringUtils.isBlank(dto.getSigla())) {
            throw new Exception("O campo sigla é obrigatório.");
        }
        
        if (dto.getSigla().length() > 2) {
            throw new Exception("O campo sigla deve conter apenas dois caracteres.");
        }
        
        if (service.count(dto.getId(), dto.getSigla()) > 0) {
            throw new Exception("Esta sigla já foi informada anteriormente.");
        }
    }
    
}
