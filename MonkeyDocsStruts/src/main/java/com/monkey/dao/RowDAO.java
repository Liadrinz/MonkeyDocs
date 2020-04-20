package com.monkey.dao;

import com.monkey.dao.base.CrudDAO;
import com.monkey.entity.Row;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository("rowDAO")
@Transactional
public class RowDAO extends CrudDAO<Integer, Row> {
    public RowDAO() { super(Row.class); }
}
