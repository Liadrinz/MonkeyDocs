package com.monkey.service;

import com.google.gson.Gson;
import com.monkey.dao.HistoryDAO;
import com.monkey.entity.Packet;
import com.monkey.manager.ClientManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HandlerService {
    @Autowired
    private ClientManager clientManager;
    @Autowired
    private HistoryDAO historyDAO;
    @Autowired
    private HistoryService historyService;
    private static final Gson gson = new Gson();

    private Packet handleDelta(Packet packet) {
        historyDAO.push(packet);
        return new Packet("mod", packet.getDocId(), packet.getUserId(), packet.getPayload());
    }

    private Packet handleReq(Packet packet) {
        String total = historyDAO.range(packet.getDocId());
        return new Packet("res", null, null, total);
    }
    
    private Packet handleSave(Packet packet) {
        historyService.persist(packet.getDocId());
        return new Packet("ack", null, null, "");
    }

    public Packet handle(Packet packet) {
        if (clientManager.getItem(packet.getUserId(), packet.getDocId()).isNew()) {
            historyService.load(packet.getDocId());
        }
        switch (packet.getKind()) {
            case "delta":
                return handleDelta(packet);
            case "req":
                return handleReq(packet);
            case "save":
                return handleSave(packet);
        }
        return null;
    }
}
