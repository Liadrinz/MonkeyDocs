package com.monkey.service;

import com.monkey.dao.DeltaDAO;
import com.monkey.dao.HistoryDAO;
import com.monkey.entity.Delta;
import com.monkey.entity.Message;
import com.monkey.manager.ClientManager;
import com.monkey.ot.OT;
import com.monkey.ot.OTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    @Autowired
    private DispatcherService dispatcherService;
    @Autowired
    private OT ot;

    public synchronized void handleDelta(Delta delta, Delta oldDelta) {
        Message msg = new Message();
        msg.type = "mod";
        msg.payload = delta;
        ot.apply(delta, oldDelta);
        dispatcherService.broadcast(msg, delta.getDocid());
        historyDAO.push(delta);
    }

    public String handleReq(int docId) {
        return historyDAO.range(docId);
//        if (clientManager.getDocStatus(docId) == ClientManager.DocStatus.READY) {
//            return historyDAO.range(docId);
//        } else if (clientManager.getDocStatus(docId) == ClientManager.DocStatus.PERSIST) {
//            clientManager.setDocStatus(docId, ClientManager.DocStatus.LOADING);
//            historyService.load(docId);
//        }
//        Delta ex = new Delta();
//        ex.setDocid(docId);
//        return JSONArray.toJSONString(deltaDAO.findByExample(ex));
    }

    public synchronized void handleAck(int docId) {

    }
    
    public void handleSave(int docId) {

    }
}
