package com.monkey.service;

import com.google.gson.Gson;
import com.monkey.dao.HistoryDAO;
import com.monkey.entity.Packet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HandlerService {
    @Autowired
    private HistoryDAO historyDAO;
    private static final Gson gson = new Gson();

    private Packet handleDelta(Packet packet) {
        historyDAO.push(packet);
        return new Packet("mod", packet.getDocId(), packet.getUserId(), packet.getPayload());
    }

    private Packet handleReq(Packet packet) {
        String total = historyDAO.range(packet.getDocId());
        return new Packet("res", null, null, total);
    }

    public Packet handle(Packet packet) {
        switch (packet.getKind()) {
            case "delta":
                return handleDelta(packet);
            case "req":
                return handleReq(packet);
        }
        return null;
    }
}
