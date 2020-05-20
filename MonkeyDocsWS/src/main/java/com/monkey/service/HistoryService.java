package com.monkey.service;

import com.google.gson.Gson;
import com.monkey.dao.DeltaDAO;
import com.monkey.dao.HistoryDAO;
import com.monkey.dao.MetaDAO;
import com.monkey.dao.UserDAO;
import com.monkey.entity.Delta;
import com.monkey.entity.Packet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Component
public class HistoryService {
    private static final Gson gson = new Gson();
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private HistoryDAO historyDAO;
    @Autowired
    private DeltaDAO deltaDAO;
    @Autowired
    private MetaDAO metaDAO;
    public synchronized void load(int docId) {
        this.unload(docId);
        Set<Delta> deltas = metaDAO.findOne(docId).getRefDeltas();
        assert deltas != null;
        for (Delta delta : deltas) {
            Packet packet = new Packet();
            packet.setDocId(delta.getDocid());
            packet.setUserId(delta.getUserid());
            packet.setPayload(delta.getContent());
            packet.setKind("load");
            packet.setTime(new Date());
            historyDAO.push(packet);
        }
    }
    public synchronized void unload(int docId) {
        historyDAO.del(docId);
    }
    public synchronized void persist(int docId) {
        deltaDAO.deleteAll(metaDAO.findOne(docId).getRefDeltas());
        List<String> deltas = historyDAO.list(docId);
        List<Delta> resultsToSave = new ArrayList<>();
        for (String delta : deltas) {
            Packet packet = gson.fromJson(delta, Packet.class);
            Delta deltaEntity = new Delta();
            deltaEntity.setContent(packet.getPayload());
            deltaEntity.setRefMeta(metaDAO.findOne(packet.getDocId()));
            deltaEntity.setRefUser(userDAO.findOne(packet.getUserId()));
            resultsToSave.add(deltaEntity);
        }
        deltaDAO.createAll(resultsToSave);
    }
}
