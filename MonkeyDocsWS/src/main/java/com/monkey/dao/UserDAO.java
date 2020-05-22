package com.monkey.dao;

import com.monkey.dao.base.CrudDAO;
import com.monkey.entity.User;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository("userDAO")
@Transactional
public class UserDAO extends CrudDAO<Integer, User> {
    public UserDAO() {
        super(User.class);
    }
}

