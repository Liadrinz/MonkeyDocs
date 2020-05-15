package com.monkey.dao.base;

import com.monkey.entity.base.BaseEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Transactional
public abstract class ManyToManyDAO<M extends BaseEntity<M>, T extends Serializable> {
    @Autowired
    protected SessionFactory sessionFactory;
    private Class clazz;
    protected ManyToManyDAO(Class clazz) {
        this.clazz = clazz;
    }
    // Create
    public M create(M entity) {
        Session session = sessionFactory.getCurrentSession();
        session.save(entity);
        return entity;
    }
    // Read
    public List<M> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(clazz);
        return criteria.list();
    }
    public List<M> findByExample(M entity) {
        Example example = Example.create(entity).ignoreCase();
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(entity.getClass()).add(example).list();
    }
    protected List<T> findAll(String tableName, String toField, String byField, int otherId) {
        String hql = String.format("select %s from %s where %s=" + otherId, toField, tableName, byField);
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hql);
        return query.list();
    }
    // Delete
    public M deleteByExample(M example) {
        Session session = sessionFactory.getCurrentSession();
        Object entity = findByExample(example);
        session.delete(entity);
        return (M)entity;
    }
}
