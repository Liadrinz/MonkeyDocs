package com.monkey.dao;

import com.monkey.dao.base.CrudDAO;
import com.monkey.entity.Delta;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository("deltaDAO")
@Transactional
public class DeltaDAO extends CrudDAO<Integer, Delta> {
    public DeltaDAO() {
        super(Delta.class);
    }
    public void createAll(Iterable<Delta> deltas) {
        for (Delta delta : deltas) {
            create(delta);
        }
    }
    public void deleteAll(Iterable<Delta> deltas) {
        for (Delta delta : deltas) {
            deleteOne(delta.getId());
        }
    }
}
