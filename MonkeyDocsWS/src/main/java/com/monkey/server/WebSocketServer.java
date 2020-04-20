package com.monkey.server;

import com.monkey.service.ServiceUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/collaborate/{docId}")
@Component
public class WebSocketServer {
    @Autowired
    private ServiceUnit serviceUnit;
    private Session session;
    private String docId;
    private static int onlineCount = 0;
    private static ConcurrentHashMap<String, WebSocketServer> serverMap = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("docId") String docId) {
        this.session = session;
        this.docId = docId;
        if (serverMap.containsKey(docId)) {
            serverMap.remove(docId);
            serverMap.put(docId, this);
        } else {
            serverMap.put(docId, this);
            addOnlineCount();
        }
    }

    @OnClose
    public void onClose() {
        if (serverMap.containsKey(docId)) {
            serverMap.remove(docId);
            subOnlineCount();
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        serviceUnit.handle(message);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    private static void addOnlineCount() { onlineCount++; }
    private static void subOnlineCount() { onlineCount--; }
}
