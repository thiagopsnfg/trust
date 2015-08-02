/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.trust.repository;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Thiago
 */
public abstract class BasicRepository {

    private static final long serialVersionUID = 1L;

    private final EntityManager em;

    public BasicRepository(EntityManager em) {
        this.em = em;
    }

    protected EntityManager getEntityManager() {
        return em;
    }

    //Criar uma nova entidade.
    protected <T> T addEntity(Class<T> classToCast, Object entity) {
        getEntityManager().persist(entity);
        return (T) entity;
    }

    //Retornar uma entidade.
    protected <T> T getEntity(Class<T> classToCast, Serializable pk) {
        return getEntityManager().find(classToCast, pk);
    }

    //Alterar uma entidade.
    protected <T> T setEntity(Class<T> classToCast, Object entity) {
        Object updateObj = getEntityManager().merge(entity);
        return (T) updateObj;
    }

    //Remover uma entidade.
    protected void removeEntity(Object entity) {
        Object updateObj = getEntityManager().merge(entity);
        getEntityManager().remove(updateObj);
    }

    //Executa qualquer query e retorna somente uma entidade de resultado.
    protected <T> T getPurePojo(Class<T> classToCast, String query, Object... values) {
        Query qr = createQuery(query, values);
        return (T) qr.getSingleResult();

    }

    //Executa qualquer query e retorna uma lista de entidades.
    protected <T> List<T> getPureList(Class<T> classToCast, String query, Object... values) {
        Query qr = createQuery(query, values);
        return qr.getResultList();
    }

    //Executa qualquer query de update e retona um int.
    protected int executCommand(String query, Object... values) {
        Query qr = createQuery(query, values);
        return qr.executeUpdate();
    }

    //Monta a query.
    private Query createQuery(String query, Object... values) {
        Query qr = getEntityManager().createQuery(query);
        for (int i = 0; i < values.length; i++) {
            qr.setParameter((i + 1), values[i]);
        }
        return qr;
    }

}
