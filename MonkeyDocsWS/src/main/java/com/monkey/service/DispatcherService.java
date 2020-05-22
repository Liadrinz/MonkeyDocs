package com.monkey.service;

import com.alibaba.fastjson.JSON;
import com.monkey.entity.Message;
import com.monkey.manager.ClientManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import java.io.IOException;

@Component
public class DispatcherService {
    @Autowired
    private ClientManager clientManager;

    public void broadcast(Message msg, Integer docId) {
        String content = JSON.toJSONString(msg);
        try {
            for (ClientManager.Item item : clientManager.getItemsByDocId(docId)) {
                item.getServer().session.getBasicRemote().sendText(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void respond(Message msg, Session session) {
        try {
            String content = JSON.toJSONString(msg);
            session.getBasicRemote().sendText(content);
        } catch (IOException e) {
            e.printStackTrace();;
        }
    }
}
