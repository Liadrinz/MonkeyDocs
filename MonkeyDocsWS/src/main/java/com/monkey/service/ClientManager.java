package com.monkey.service;

import com.monkey.pdu.ConnectionItem;
import com.monkey.pdu.Packet;

import java.util.HashMap;
import java.util.Map;

public class ClientManager {
    Map<Long, ConnectionItem> table = new HashMap<>();
    public void newClient(Packet packet, boolean latest, boolean success) {
        long wsId = packet.getWsId();
        ConnectionItem item = new ConnectionItem(wsId, packet.getDocId(), packet.getUserId(), System.currentTimeMillis(), latest, success);
        table.put(wsId, item);
    }
    public ConnectionItem getClient(long wsId) {
        return table.get(wsId);
    }
}
