package com.monkey.dao;

import com.monkey.entity.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
class UserDAOImpl implements UserDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User create(User entity) {
        Session session = sessionFactory.getCurrentSession();
        return findOne((Integer)session.save(entity));
    }

    @Override
    public List<User> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User");
        return query.list();
    }

    @Override
    public User findOne(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        return (User)session.get(User.class, id);
    }

    @Override
    public List<User> findByExample(User user) {
        Example example = Example.create(user).ignoreCase();
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(User.class).add(example).list();
    }

    @Override
    public User updateOne(Integer id, User entity) {
        return null;
    }

    @Override
    public User deleteOne(Integer id) {
        return null;
    }
}
