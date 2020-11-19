package com.ngn.spring.project.base;

import com.ngn.spring.project.cdb.specializedFirm.model.SpecializedFirmFinal;
import org.hibernate.Session;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Properties;


public abstract class BaseDao {
    protected String sqlQuery;
    protected org.hibernate.query.Query hQuery;
    protected EntityManager em;
    @Autowired
    @Qualifier("queryProps")
    protected Properties properties;

    @PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
    }

    protected Session getCurrentSession() {
        return em.unwrap(Session.class);
    }

    protected org.hibernate.query.Query hibernateQuery(String query, Class dtoClazz) {
        return getCurrentSession()
                .createSQLQuery(query)
                .setResultTransformer(new AliasToBeanResultTransformer(dtoClazz));
    }

    protected org.hibernate.query.Query hibernateQuery(String query) {
        return getCurrentSession().createSQLQuery(query);
    }

    protected Query persistenceQuery(String query, Class entityClazz) {
        return em.createNativeQuery(query, entityClazz);
    }

    protected Query persistenceQuery(String query) {
        return em.createNativeQuery(query);
    }

    @Transactional
    public void saveOrUpdate(Object obj) {
        getCurrentSession().saveOrUpdate(obj);
    }

    public void save(Object obj) {
        getCurrentSession().save(obj);
    }

    protected void deleteE(Object obj) {
        em.remove(obj);
    }

    protected org.hibernate.query.Query sqlQuery(String query) {
        return getCurrentSession().createSQLQuery(query);
    }
    protected org.hibernate.query.Query sqlQuery(String query, Class dtoClazz) {
        return getCurrentSession()
                .createSQLQuery(query)
                .setResultTransformer(new AliasToBeanResultTransformer(dtoClazz));
    }

}

