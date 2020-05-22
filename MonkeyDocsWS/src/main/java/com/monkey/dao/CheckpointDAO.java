package com.monkey.dao;

import com.monkey.dao.base.CrudDAO;
import com.monkey.entity.Checkpoint;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository("checkpointDAO")
@Transactional
public class CheckpointDAO extends CrudDAO<Integer, Checkpoint> {
    public CheckpointDAO() {
        super(Checkpoint.class);
    }
}
