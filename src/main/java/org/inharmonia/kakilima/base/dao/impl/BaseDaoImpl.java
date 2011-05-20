package org.inharmonia.kakilima.base.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.inharmonia.kakilima.base.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.SessionFactoryUtils;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

public abstract class BaseDaoImpl<T extends Serializable> implements BaseDao<T> {
    private Class<T> entityClass;

    @Autowired(required = true)
    private SessionFactory sessionFactory;

    protected final Session getSession(){
        return SessionFactoryUtils.getSession(sessionFactory, true);    
    }

    public BaseDaoImpl(){
        this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public final T read(Long id){
        return (T) getSession().get(entityClass, id);
    }

    @Override
    public final Long create(T transientObject){
        return (Long) getSession().save(transientObject);
    }

    @Override
    public final void update(T transientObject){
        getSession().update(transientObject);
    }

    @Override
    public final void delete(T persistentObject){
        getSession().delete(persistentObject);
    }

    protected final Criteria createCriteria(){
        return getSession().createCriteria(entityClass);
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
