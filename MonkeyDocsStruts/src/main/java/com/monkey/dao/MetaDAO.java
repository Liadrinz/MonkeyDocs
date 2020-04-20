package com.monkey.dao;

import com.monkey.dao.base.CrudDAO;
import com.monkey.entity.Meta;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository("metaDAO")
@Transactional
public class MetaDAO extends CrudDAO<Integer, Meta> {
    public MetaDAO() { super(Meta.class); }
}
