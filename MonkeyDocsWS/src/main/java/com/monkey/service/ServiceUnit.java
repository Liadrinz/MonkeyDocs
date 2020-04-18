package com.monkey.service;

import com.monkey.pdu.Packet;
import org.springframework.stereotype.Service;

@Service
public class ServiceUnit {
    private ClientManager manager = new ClientManager();
    private Handler handler = new Handler();
    private Dispatcher dispatcher = new Dispatcher();

    public void onReceive(Packet packet) {
       handler.handle(packet, manager);
    }

    public void onSendBack(Packet packet) {
        dispatcher.sendBack(packet);
    }

    public void onBroadcast(Packet packet) {
        dispatcher.broadcast(manager, packet);
    }

    public ClientManager getManager() {
        return manager;
    }

    public Handler getHandler() {
        return handler;
    }

    public Dispatcher getDispatcher() {
        return dispatcher;
    }
}
