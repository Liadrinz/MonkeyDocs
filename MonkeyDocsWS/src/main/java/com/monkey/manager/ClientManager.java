package com.monkey.manager;

import com.monkey.endpoint.WebSocketServer;
import com.monkey.entity.Delta;
import com.monkey.service.HandlerService;
import com.monkey.service.MigrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

@Component
public class ClientManager {
    @Autowired
    HandlerService handlerService;
    public enum DocStatus {
        PERSIST, LOADING, READY
    }
    private final Queue<Delta> broadcastBuffer = new ConcurrentLinkedQueue<>();
    private final ConcurrentHashMap<Integer, ConcurrentHashMap<Integer, ConcurrentLinkedQueue<Delta>>> broadcastMap = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Integer, Boolean> docLoaded = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Integer, DocStatus> docStatus = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Integer, List<Item>> docItemMap = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Integer, List<Item>> userItemMap = new ConcurrentHashMap<>();
    public class Item {
        private WebSocketServer server;
        private int userId;
        private int docId;
        private boolean loaded;
        private void putToMap(Integer field, ConcurrentHashMap<Integer, List<Item>> map) {
            List<Item> items = map.get(field);
            if (items == null)
                items = new ArrayList<>();
            items.add(this);
            map.put(field, items);
        }
        public Item(WebSocketServer server, int userId, int docId, boolean loaded) {
            this.server = server;
            this.userId = userId;
            this.docId = docId;
            this.loaded = loaded;
            putToMap(this.userId, userItemMap);
            putToMap(this.docId, docItemMap);
        }
        public WebSocketServer getServer() { return server; }
        public void setServer(WebSocketServer server) { this.server = server; }
        public int getUserId() { return userId; }
        public void setUserId(int userId) { this.userId = userId; }
        public int getDocId() { return docId; }
        public void setDocId(int docId) { this.docId = docId; }
        public boolean isLoaded() { return loaded; }
        public void setLoaded(boolean loaded) { this.loaded = loaded; }
    }
    public void newClient(int userId, int docId, WebSocketServer server) {
        new Item(server, userId, docId, true);
    }
    public void clearClient(int userId, int docId) {
        docStatus.remove(docId);
        List<Item> userItems = userItemMap.get(userId);
        List<Item> docItems = docItemMap.get(docId);
        Item target = getItem(userId, docId);
        userItems.remove(target);
        if (userItems.size() == 0) userItemMap.remove(userId);
        docItems.remove(target);
        if (docItems.size() == 0) docItemMap.remove(docId);
    }
    public List<Item> getItemsByDocId(int docId) {
        if (docItemMap.containsKey(docId))
            return docItemMap.get(docId);
        return null;
    }
    public List<Item> getItemsByUserId(int userId) {
        if (userItemMap.containsKey(userId))
            return userItemMap.get(userId);
        return null;
    }
    public Item getItem(int userId, int docId) {
        List<Item> userItems = userItemMap.get(userId);
        List<Item> docItems = docItemMap.get(docId);
        if (userItems == null || docItems == null) return null;
        userItems.retainAll(docItems);
        if (userItems.size() == 0) return null;
        return userItems.get(0);
    }
    public DocStatus getDocStatus(int docId) {
        if (!docStatus.containsKey(docId)) return DocStatus.PERSIST;
        return docStatus.get(docId);
    }
    public void setDocStatus(int docId, DocStatus value) {
        if (value == DocStatus.PERSIST) docStatus.remove(docId);
        else docStatus.put(docId, value);
    }

    public Queue<Delta> getBroadcastBuffer() {
        return broadcastBuffer;
    }
}
