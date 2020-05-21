package com.monkey.service;

import com.alibaba.fastjson.JSON;
import com.monkey.dao.DeltaDAO;
import com.monkey.dao.HistoryDAO;
import com.monkey.dao.MetaDAO;
import com.monkey.dao.UserDAO;
import com.monkey.entity.Delta;
import com.monkey.entity.Meta;
import com.monkey.manager.ClientManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HistoryService {
    @Autowired
    private ClientManager clientManager;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private HistoryDAO historyDAO;
    @Autowired
    private DeltaDAO deltaDAO;
    @Autowired
    private DeltaService deltaService;
    @Autowired
    private MetaDAO metaDAO;
    public class LoadHistory implements Runnable {
        private final int docId;
        public LoadHistory(int docId) {
            this.docId = docId;
        }
        @Override
        public void run() {
            List<String> buffer = historyDAO.list(docId);
            HistoryService.this.unload(docId);
            Meta meta = metaDAO.findOne(docId);
            Delta example = new Delta();
            example.setDocid(meta.getId());
            List<Delta> deltas = deltaDAO.findByExample(example);
            assert deltas != null;
            historyDAO.push(docId, deltas);
            historyDAO.pushes(docId, buffer);
            clientManager.setDocStatus(docId, ClientManager.DocStatus.READY);
        }
    }
    public void load(int docId) {
        new Thread(new LoadHistory(docId)).start();
    }
    public void unload(int docId) {
        historyDAO.del(docId);
        clientManager.setDocStatus(docId, ClientManager.DocStatus.PERSIST);
    }
    public void persist(int docId) {
        Meta meta = metaDAO.findOne(docId);
        Delta example = new Delta();
        example.setDocid(meta.getId());
//        deltaService.deleteAll(deltaDAO.findByExample(example));
        deltaService.createAll(JSON.parseArray(historyDAO.range(docId), Delta.class));
    }
}
