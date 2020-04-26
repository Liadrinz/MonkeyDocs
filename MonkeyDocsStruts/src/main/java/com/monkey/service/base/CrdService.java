package com.monkey.service.base;

import com.monkey.dao.base.CrdDAO;
import com.monkey.dao.base.CrudDAO;
import com.monkey.entity.base.BaseEntity;

import java.util.List;

public class CrdService<T extends BaseEntity<T>> {
    protected CrdDAO<T> dao;
    public List<T> getAll() { return dao.findAll(); }
    public List<T> getByModel(T model) { return dao.findByExample(model); }
    public T create(T model) { return dao.create(model); }
    public T delete(T model) { return dao.deleteByExample(model); }
}
