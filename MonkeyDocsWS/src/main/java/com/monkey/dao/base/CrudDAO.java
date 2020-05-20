package com.monkey.dao.base;

import com.monkey.entity.base.BaseEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
        Session session = sessionFactory.openSession();
        session.save(entity);
        return findOne((K)session.save(entity));
    }
    // Read
    public List<V> findAll() {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(clazz);
        return criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }
    public V findOne(K id) {
        Session session = sessionFactory.openSession();
        return (V)session.get(clazz, id);
    }
    public List<V> findByExample(V entity) {
        Example example = Example.create(entity).ignoreCase();
        Session session = sessionFactory.openSession();
        return session.createCriteria(entity.getClass()).add(example).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }
    // Update
    public V updateOne(K id, V model) {
        Session session = sessionFactory.openSession();
        V entity = findOne(id);
        entity.updateFrom(model);
        session.update(entity);
        return entity;
    }
    // Delete
    public V deleteOne(K id) {
        Session session = sessionFactory.openSession();
        Object entity = findOne(id);
        session.delete(entity);
        return (V)entity;
    }
}
