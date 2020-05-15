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
    public boolean persistAll(List<Delta> deltas) {
        try {
            for (Delta delta : deltas) {
                create(delta);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
