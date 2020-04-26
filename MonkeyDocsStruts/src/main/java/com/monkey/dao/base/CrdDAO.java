package com.monkey.dao.base;

import com.monkey.entity.base.BaseEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public abstract class CrdDAO<V extends BaseEntity<V>> {
    @Autowired
    protected SessionFactory sessionFactory;
    private Class clazz;
    protected CrdDAO(Class clazz) {
        this.clazz = clazz;
    }
    // Create
    public V create(V entity) {
        Session session = sessionFactory.getCurrentSession();
        session.save(entity);
        return entity;
    }
    // Read
    public List<V> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(clazz);
        return criteria.list();
    }
    public List<V> findByExample(V entity) {
        Example example = Example.create(entity).ignoreCase();
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(entity.getClass()).add(example).list();
    }
    // Delete
    public V deleteByExample(V example) {
        Session session = sessionFactory.getCurrentSession();
        Object entity = findByExample(example);
        session.delete(entity);
        return (V)entity;
    }
}
