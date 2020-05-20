package com.monkey.service;

import com.monkey.dao.DeltaDAO;
import com.monkey.entity.Delta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeltaService {
    @Autowired
    private DeltaDAO deltaDAO;
    public void createAll(Iterable<Delta> deltas) {
        for (Delta delta : deltas) {
            deltaDAO.create(delta);
        }
    }
    public void deleteAll(Iterable<Delta> deltas) {
        for (Delta delta : deltas) {
            deltaDAO.deleteOne(delta.getId());
        }
    }
}
