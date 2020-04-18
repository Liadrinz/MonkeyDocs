package com.monkey.service;

import com.monkey.pdu.Packet;

public class Dispatcher {
    public void send(long wsId, Packet packet) {

    }

    public void sendBack(Packet packet) {
        // TODO: Fetch data from redis
        send(packet.getWsId(), packet);
    }

    public void broadcast(ClientManager manager, Packet packet) {
        // TODO: Concat the changes with the original content
        for (long id : manager.table.keySet()) {
            if (id != packet.getWsId()) {
                send(id, packet);
            }
        }
    }
}
