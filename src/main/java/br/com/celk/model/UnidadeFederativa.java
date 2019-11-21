/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.celk.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author kelvin
 */
@Entity
@Table(name = "unidade_federativa")
@NamedQueries({
    @NamedQuery(name = "UnidadeFederativa.findAll", query = "SELECT u FROM UnidadeFederativa u")
    , @NamedQuery(name = "UnidadeFederativa.findByIdUnidadeFederativa", query = "SELECT u FROM UnidadeFederativa u WHERE u.idUnidadeFederativa = :idUnidadeFederativa")
    , @NamedQuery(name = "UnidadeFederativa.findBySigla", query = "SELECT u FROM UnidadeFederativa u WHERE u.sigla = :sigla")
    , @NamedQuery(name = "UnidadeFederativa.findByNome", query = "SELECT u FROM UnidadeFederativa u WHERE u.nome = :nome")
    , @NamedQuery(name = "UnidadeFederativa.findByDataHoraAtualizacao", query = "SELECT u FROM UnidadeFederativa u WHERE u.dataHoraAtualizacao = :dataHoraAtualizacao")})
public class UnidadeFederativa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_unidade_federativa")
    private Integer idUnidadeFederativa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "sigla")
    private String sigla;
    @Size(max = 255)
    @Column(name = "nome")
    private String nome;
    @Column(name = "data_hora_atualizacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraAtualizacao;

    public UnidadeFederativa() {
    }

    public UnidadeFederativa(Integer idUnidadeFederativa) {
        this.idUnidadeFederativa = idUnidadeFederativa;
    }

    public UnidadeFederativa(Integer idUnidadeFederativa, String sigla) {
        this.idUnidadeFederativa = idUnidadeFederativa;
        this.sigla = sigla;
    }

    public Integer getIdUnidadeFederativa() {
        return idUnidadeFederativa;
    }

    public void setIdUnidadeFederativa(Integer idUnidadeFederativa) {
        this.idUnidadeFederativa = idUnidadeFederativa;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataHoraAtualizacao() {
        return dataHoraAtualizacao;
    }

    public void setDataHoraAtualizacao(Date dataHoraAtualizacao) {
        this.dataHoraAtualizacao = dataHoraAtualizacao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUnidadeFederativa != null ? idUnidadeFederativa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UnidadeFederativa)) {
            return false;
        }
        UnidadeFederativa other = (UnidadeFederativa) object;
        if ((this.idUnidadeFederativa == null && other.idUnidadeFederativa != null) || (this.idUnidadeFederativa != null && !this.idUnidadeFederativa.equals(other.idUnidadeFederativa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.kopp.model.UnidadeFederativa[ idUnidadeFederativa=" + idUnidadeFederativa + " ]";
    }
    
}
