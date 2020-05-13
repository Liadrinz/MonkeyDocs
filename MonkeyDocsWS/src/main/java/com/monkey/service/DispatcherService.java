package com.monkey.service;

import com.google.gson.Gson;
import com.monkey.entity.Packet;
import com.monkey.routing.ClientManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.websocket.Session;
import java.io.IOException;

@Component
public class DispatcherService {
    @Autowired
    private ClientManager clientManager;
    private static final Gson gson = new Gson();

    public void broadcast(Packet packet, Integer docId) {
        String content = gson.toJson(packet);
        try {
            for (ClientManager.Item item : clientManager.getItemsByDocId(docId)) {
                item.getServer().session.getBasicRemote().sendText(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void respond(Packet packet, Session session) {
        try {
            String content = gson.toJson(packet);
            session.getBasicRemote().sendText(content);
        } catch (IOException e) {
            e.printStackTrace();;
        }
    }
}
