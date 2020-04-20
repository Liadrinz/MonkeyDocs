package com.monkey.manager;

import com.monkey.pdu.Packet;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ClientManager {
    Map<Long, ConnectionItem> table = new ConcurrentHashMap<>();
    public void newClient(Packet packet, boolean latest, boolean success) {
        long wsId = packet.getWsId();
        ConnectionItem item = new ConnectionItem(wsId, packet.getDocId(), packet.getUserId(), System.currentTimeMillis(), latest, success);
        table.put(wsId, item);
    }
    public ConnectionItem getClient(long wsId) {
        return table.get(wsId);
    }
}
