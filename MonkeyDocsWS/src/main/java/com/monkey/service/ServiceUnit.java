package com.monkey.service;

import com.monkey.pdu.Packet;
import com.monkey.manager.ClientManager;
import com.monkey.service.protocol.Protocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceUnit implements Protocol {
    @Autowired
    private Handler handler;
    @Autowired
    private Dispatcher dispatcher;

    private ClientManager manager = new ClientManager();

    @Override
    public void handle(String message) {
        Packet packet = Packet.getInstance(message);
        handler.handle(packet, manager);
    }

    @Override
    public Packet receive() {
        String respMessage = dispatcher.dispatch(manager);
        return Packet.getInstance(respMessage);
    }
}
