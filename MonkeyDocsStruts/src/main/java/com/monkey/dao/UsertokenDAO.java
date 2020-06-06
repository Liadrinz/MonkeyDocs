package com.monkey.dao;

import com.monkey.dao.base.CrudDAO;
import com.monkey.entity.Usertoken;
import org.springframework.stereotype.Repository;

@Repository
public class UsertokenDAO extends CrudDAO<Integer, Usertoken> {
    protected UsertokenDAO() {
        super(Usertoken.class);
    }
}
