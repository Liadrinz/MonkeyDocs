package com.monkey.endpoint;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/collaborate/{docId}")
@Component
public class WebSocketServer {
    private Session session;
    private Integer docId;
    private static int onlineCount = 0;

    private static final ConcurrentHashMap<Integer, WebSocketServer> userServerMap = new ConcurrentHashMap<Integer, WebSocketServer>();

    @OnOpen
    public void onOpen(Session session, @PathParam("docId") Integer userId) {
        this.session = session;
        this.docId = userId;
        if (userServerMap.containsKey(userId)) {
            userServerMap.remove(userId);
            userServerMap.put(userId, this);
        } else {
            userServerMap.put(userId, this);
            addOnlineCount();
        }
    }

    @OnClose
    public void onClose() {
        if (userServerMap.containsKey(docId)) {
            userServerMap.remove(docId);
            subOnlineCount();
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) {

    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    private static void addOnlineCount() { onlineCount++; }
    private static void subOnlineCount() { onlineCount--; }
}
