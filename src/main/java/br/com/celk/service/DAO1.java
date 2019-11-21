/*
 * Setor de pesquisa e desenvolvimento - Kopp Tecnologia.
 */
package br.com.celk.service;

import java.util.Properties;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.eclipse.persistence.config.PersistenceUnitProperties;

/**
 *
 * @author Kelvin Pereira Alves <kelvinpalves@gmail.com>
 */
public class DAO1 {

//    private static EntityManagerFactory emf = null;
    private static EntityManagerFactory emf = configure("CELKPU");
    private static final ThreadLocal<EntityManager> session = new ThreadLocal<>();

    protected DAO1() {
    }

    public static EntityManagerFactory configure(String persistenceUnit) {
        Properties props = new Properties();
        props.put(PersistenceUnitProperties.JDBC_URL, "jdbc:mysql://localhost:3306/celk");
        props.put(PersistenceUnitProperties.JDBC_USER, "forge");
        props.put(PersistenceUnitProperties.JDBC_PASSWORD, "root");
        props.put(PersistenceUnitProperties.JDBC_DRIVER, "com.mysql.jdbc.Driver");
        props.put(PersistenceUnitProperties.ALLOW_ZERO_ID, "true");

        props.put(PersistenceUnitProperties.LOGGING_LEVEL, "SEVERE");
        props.put(PersistenceUnitProperties.LOGGING_PARAMETERS, "true");
        props.put(PersistenceUnitProperties.CACHE_SIZE_DEFAULT, "0");
        props.put(PersistenceUnitProperties.CACHE_SHARED_DEFAULT, "false");
        props.put(PersistenceUnitProperties.CACHE_TYPE_DEFAULT, "NONE");
        
        return Persistence.createEntityManagerFactory(persistenceUnit, props);
    }

    public static EntityManager getEM() {
        EntityManager em = (EntityManager) DAO1.session.get();
        if (em == null) {
            em = emf.createEntityManager();
            DAO1.session.set(em);
        }
        return em;
    }

    public static EntityManager getNewEM() {
        return emf.createEntityManager();
    }

    public static void close() {
        if (DAO1.session.get() == null) {
            return;
        }

        getEM().close();
        DAO1.session.set(null);
    }

    public static void begin() {
        getEM().getTransaction().begin();
    }

    public static void commit() {
        getEM().getTransaction().commit();
        close();
    }

    public static void rollback() {
        try {
            if (getEM().getTransaction().isActive()) {
                getEM().getTransaction().rollback();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static boolean isConnectionOk() {
        try {
            DAO1.getNewEM().close();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ie) {
                ex.printStackTrace();
            }
            return false;
        }
    }
}
