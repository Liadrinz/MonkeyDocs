package com.monkey.service;

import com.monkey.manager.ClientManager;
import com.monkey.pdu.Packet;
import org.springframework.stereotype.Service;

@Service
public class Handler {
    public void handle(Packet packet, ClientManager manager) {
        switch (packet.getType()) {
            case REQ:
                manager.newClient(packet, true, false);
                break;
            case ACK:
                manager.getClient(packet.getWsId()).setSuccess(true);
                break;
        }
        // TODO: Redis Access
        // TODO: MySQL Access
    }
}
