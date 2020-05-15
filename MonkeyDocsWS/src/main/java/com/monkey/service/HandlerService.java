package com.monkey.service;

import com.google.gson.Gson;
import com.monkey.dao.DeltaDAO;
import com.monkey.dao.HistoryDAO;
import com.monkey.entity.Delta;
import com.monkey.entity.Packet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HandlerService {
    @Autowired
    private DeltaDAO deltaDAO;
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
    
    private Packet handleSave(Packet packet) {
        List<String> deltaContents = historyDAO.list(packet.getDocId());
        List<Delta> deltas = new ArrayList<>();
        for (String content : deltaContents) {
            Packet eachPacket = gson.fromJson(content, Packet.class);
            Delta delta = new Delta();
            delta.setUserid(eachPacket.getUserId());
            delta.setContent(eachPacket.getPayload());
            delta.setDocid(eachPacket.getDocId());
            deltas.add(delta);
        }
        deltaDAO.persistAll(deltas);
        return new Packet("ack", null, null, "");
    }

    public Packet handle(Packet packet) {
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
