package com.monkey.dao.base;

import com.monkey.entity.base.BaseEntity;
import org.hibernate.*;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

// CRUD Generic
@Transactional
public abstract class CrudDAO<K extends Serializable, V extends BaseEntity> {
    @Autowired
    protected SessionFactory sessionFactory;
    private Class clazz;
    protected CrudDAO(Class clazz) {
        this.clazz = clazz;
    }
    // Create
    public V create(V entity) {
        Session session = sessionFactory.getCurrentSession();
//        Transaction tx = session.beginTransaction();
        session.save(entity);
        V result = findOne((K)session.save(entity));
//        tx.commit();
        return result;
    }
    // Read
    public List<V> findAll() {
        Session session = sessionFactory.getCurrentSession();
//        Transaction tx = session.beginTransaction();
        Criteria criteria = session.createCriteria(clazz);
        List<V> result = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
//        tx.commit();
        return result;
    }
    public V findOne(K id) {
        Session session = sessionFactory.getCurrentSession();
//        Transaction tx = session.beginTransaction();
        V result = (V)session.get(clazz, id);
//        tx.commit();
        return result;
    }
    public List<V> findByExample(V entity) {
        Session session = sessionFactory.getCurrentSession();
//        Transaction tx = session.beginTransaction();
        Example example = Example.create(entity).ignoreCase();
        List<V> result = session.createCriteria(entity.getClass()).add(example).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
//        tx.commit();
        return result;
    }
    // Update
    public V updateOne(K id, V model) {
        Session session = sessionFactory.getCurrentSession();
//        Transaction tx = session.beginTransaction();
        V entity = findOne(id);
        entity.updateFrom(model);
        session.update(entity);
//        tx.commit();
        return entity;
    }
    // Delete
    public V deleteOne(K id) {
        Session session = sessionFactory.getCurrentSession();
//        Transaction tx = session.beginTransaction();
        Object entity = findOne(id);
        session.delete(entity);
//        tx.commit();
        return (V)entity;
    }
}
