package com.monkey.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.monkey.dao.DeltaDAO;
import com.monkey.dao.HistoryDAO;
import com.monkey.entity.Delta;
import com.monkey.entity.Packet;
import com.monkey.manager.ClientManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HandlerService {
    @Autowired
    private ClientManager clientManager;
    @Autowired
    private HistoryDAO historyDAO;
    @Autowired
    private DeltaDAO deltaDAO;
    @Autowired
    private HistoryService historyService;

    public Delta handleDelta(Delta delta) {
        historyDAO.push(delta);
        return delta;
    }

    public String handleReq(int docId) {
        if (clientManager.getDocStatus(docId) == ClientManager.DocStatus.READY) {
            return historyDAO.range(docId);
        } else if (clientManager.getDocStatus(docId) == ClientManager.DocStatus.PERSIST) {
            clientManager.setDocStatus(docId, ClientManager.DocStatus.LOADING);
            historyService.load(docId);
        }
        Delta ex = new Delta();
        ex.setDocid(docId);
        return JSONArray.toJSONString(deltaDAO.findByExample(ex));
    }
    
    public void handleSave(int docId) {
        historyService.persist(docId);
    }
}
