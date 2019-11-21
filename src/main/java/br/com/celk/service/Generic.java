/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.celk.service;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author kelvin
 */
public class Generic<T> {
    
    private final Class<T> persistentClass;

    public Generic(boolean clear) {
        this.persistentClass = (Class<T>) ((ParameterizedType) (getClass().getGenericSuperclass())).getActualTypeArguments()[0];
        
        if (clear) {
            try {
                DAO1.rollback();
                DAO1.close();
            } catch (Exception ex) {
                DAO1.rollback();
                DAO1.close();
            }
        }
        
    }

    public Generic(final Class<T> persistentClass) {
        super();
        this.persistentClass = persistentClass;
        
        try {
            DAO1.rollback();
            DAO1.close();
        } catch (Exception ex) {
            DAO1.rollback();
            DAO1.close();
        }
        
    }
    
    public Generic(final Class<T> persistentClass, boolean f) {
        super();
        this.persistentClass = persistentClass;
    }

    public List<T> findAll() {
        try {
            String sql = "SELECT t FROM " + persistentClass.getSimpleName() + " t";
            Query q = DAO1.getEM().createQuery(sql);
            return q.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Collections.EMPTY_LIST;
        }

    }

    public long count(Integer id, String sigla) {
        try {
            String sql = "SELECT count(t) FROM " + persistentClass.getSimpleName() + " t WHERE t.sigla = :sigla ";
            
            if (id != null) {
                sql += " AND t.id" + persistentClass.getSimpleName() + " <> :id";
            }
            
            Query q = DAO1.getEM().createQuery(sql);
            
            q.setParameter("sigla", sigla);
            
            if (id != null) {
                q.setParameter("id", id);
            }
            
            return (long) q.getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }

    }

    public List<T> findRange(int range[]) {
        try {
            String sql = "SELECT t FROM " + persistentClass.getSimpleName() + " t";
            Query q = DAO1.getEM().createQuery(sql);

            return q.setFirstResult(range[1])
                    .setMaxResults(range[2])
                    .getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Collections.EMPTY_LIST;
        }
    }
    
}
