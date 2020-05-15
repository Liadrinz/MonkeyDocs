package com.monkey.endpoint;

import com.google.gson.Gson;
import com.monkey.entity.Packet;
import com.monkey.routing.ClientManager;
import com.monkey.service.DispatcherService;
import com.monkey.service.HandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint("/collaborate/{docId}/{userId}")
@Component
public class WebSocketServer {
    private static ClientManager clientManager;
    private static HandlerService handlerService;
    private static DispatcherService dispatcherService;
    @Autowired
    public void setClientManager(ClientManager clientManager) {
        WebSocketServer.clientManager = clientManager;
    }
    @Autowired
    public void setHandlerService(HandlerService handlerService) {
        WebSocketServer.handlerService = handlerService;
    }
    @Autowired
    public void setDispatcherService(DispatcherService dispatcherService) {
        WebSocketServer.dispatcherService = dispatcherService;
    }

    private static final Gson gson = new Gson();

    public Session session;
    private Integer docId;
    private Integer userId;
    private static int onlineCount = 0;

    @OnOpen
    public void onOpen(Session session, @PathParam("docId") Integer docId, @PathParam("userId") Integer userId) {
        this.session = session;
        this.docId = docId;
        this.userId = userId;
        if (clientManager.getItem(userId, docId) != null) {
            clientManager.clearClient(userId, docId);
        } else {
            addOnlineCount();
        }
        clientManager.newClient(userId, docId, this);
    }

    @OnClose
    public void onClose() {
        if (clientManager.getItem(userId, docId) != null) {
            clientManager.clearClient(userId, docId);
            subOnlineCount();
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        try {
            Packet packet = gson.fromJson(message, Packet.class);
            Packet response = handlerService.handle(packet);
            if (response == null) return;
            if (response.getKind().equals("mod")) {
                dispatcherService.broadcast(response, docId);
            } else if (response.getKind().equals("res")) {
                dispatcherService.respond(response, session);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    private static void addOnlineCount() { onlineCount++; }
    private static void subOnlineCount() { onlineCount--; }
}
