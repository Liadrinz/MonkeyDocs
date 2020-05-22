package com.monkey.dao;

import com.monkey.dao.base.CrudDAO;
import com.monkey.entity.Delta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository("deltaDAO")
@Transactional
public class DeltaDAO extends CrudDAO<Integer, Delta> {
    public DeltaDAO() {
        super(Delta.class);
    }

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private MetaDAO metaDAO;

    @Override
    public Delta create(Delta entity) {
        entity.setRefUser(userDAO.findOne(entity.getUserid()));
        entity.setRefMeta(metaDAO.findOne(entity.getDocid()));
        return super.create(entity);
    }
}
