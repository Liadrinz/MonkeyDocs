package com.monkey.endpoint;

import com.alibaba.fastjson.JSON;
import com.monkey.entity.Message;
import com.monkey.manager.ClientManager;
import com.monkey.service.DispatcherService;
import com.monkey.service.HandlerService;
import com.monkey.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/collaborate/{docId}/{userId}")
@Component
public class WebSocketServer {
    private static ClientManager clientManager;
    private static HandlerService handlerService;
    private static DispatcherService dispatcherService;
    private static HistoryService historyService;
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
    @Autowired
    public void setHistoryService(HistoryService historyService) {
        WebSocketServer.historyService = historyService;
    }

    public Session session;
    private Integer docId;
    private Integer userId;
    private static int onlineCount = 0;

    @OnOpen
    public void onOpen(Session session, @PathParam("docId") Integer docId, @PathParam("userId") Integer userId) {
        this.session = session;
        this.docId = docId;
        this.userId = userId;
        boolean firstUserOfThisDoc = false;
        if (clientManager.getItemsByDocId(docId) == null) {
            firstUserOfThisDoc = true;
        }
        if (clientManager.getItem(userId, docId) != null) {
            clientManager.clearClient(userId, docId);
        } else {
            addOnlineCount();
        }
        clientManager.newClient(userId, docId, this);
        clientManager.getItem(userId, docId).setNew(firstUserOfThisDoc);
    }

    @OnClose
    public void onClose() {
        if (clientManager.getItem(userId, docId) != null) {
            clientManager.clearClient(userId, docId);
            if (clientManager.getItemsByDocId(docId) == null) {
                historyService.persist(docId);
                historyService.unload(docId);
                clientManager.setDocStatus(docId, ClientManager.DocStatus.PERSIST);
            }
            subOnlineCount();
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        try {
            Message msg = JSON.parseObject(message, Message.class);
            if (msg.type.equals("delta")) {
                msg.payload = handlerService.handleDelta(msg.payload);
                msg.type = "mod";
                dispatcherService.broadcast(msg, docId);
            } else if (msg.type.equals("req")) {
                msg.optional = handlerService.handleReq(msg.payload.getDocid());
                msg.payload = null;
                msg.type = "res";
                dispatcherService.respond(msg, session);
            } else if (msg.type.equals("save")) {
                handlerService.handleSave(msg.payload.getDocid());
                msg.type = "ack";
                msg.payload = null;
                dispatcherService.respond(msg, session);
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
