package com.monkey.dao;

import java.util.List;

// CRUD Generic
public interface CrudDAO<K, V> {
    // Create
    V create(V entity);
    // Read
    List<V> findAll();
    V findOne(K id);
    List<V> findByExample(V example);
    // Update
    V updateOne(K id, V entity);
    // Delete
    V deleteOne(K id);
}
