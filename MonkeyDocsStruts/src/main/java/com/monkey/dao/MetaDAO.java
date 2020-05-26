package com.monkey.dao;

import com.monkey.dao.base.CrudDAO;
import com.monkey.entity.Meta;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;

@Repository("metaDAO")
@Transactional
public class MetaDAO extends CrudDAO<Integer, Meta> {
    public MetaDAO() {
        super(Meta.class);
    }

//    @Override
//    public Meta create(Meta entity) {
//        if (entity.getCreateTime() == null) entity.setCreateTime(new Date());
//        if (entity.getUpdateTime() == null) entity.setUpdateTime(new Date());
//        return super.create(entity);
//    }
//
//    @Override
//    public Meta updateOne(Integer id, Meta model) {
//        if (model.getUpdateTime() == null) model.setUpdateTime(new Date());
//        return super.updateOne(id, model);
//    }
}
