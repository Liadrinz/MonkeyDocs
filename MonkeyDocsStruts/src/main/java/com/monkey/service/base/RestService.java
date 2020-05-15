package com.monkey.service.base;

import com.monkey.dao.base.CrudDAO;
import com.monkey.entity.base.BaseEntity;

import java.util.List;

@Deprecated
public abstract class RestService<T extends BaseEntity> {
    protected CrudDAO<Integer, T> dao;
    public List<T> getAll() { return dao.findAll(); }
    public T getById(int id) { return dao.findOne(id); }
    public List<T> getByModel(T model) { return dao.findByExample(model); }
    public T create(T model) { return dao.create(model); }
    public T update(int id, T model) { return dao.updateOne(id, model); }
    public T delete(int id) { return dao.deleteOne(id); }
}
