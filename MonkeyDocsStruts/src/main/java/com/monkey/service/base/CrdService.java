package com.monkey.service.base;

import com.monkey.dao.base.ManyToManyDAO;
import com.monkey.entity.base.BaseEntity;

import java.io.Serializable;
import java.util.List;

public class CrdService<M extends BaseEntity<M>, T extends Serializable> {
    protected ManyToManyDAO<M, T> dao;
    public List<M> getAll() { return dao.findAll(); }
    public List<M> getByModel(M model) { return dao.findByExample(model); }
    public M create(M model) { return dao.create(model); }
    public M delete(M model) { return dao.deleteByExample(model); }
}
