package com.monkey.routing;

import com.monkey.endpoint.WebSocketServer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ClientManager {
    private final ConcurrentHashMap<Integer, List<Item>> docItemMap = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Integer, List<Item>> userItemMap = new ConcurrentHashMap<>();
    public class Item {
        private WebSocketServer server;
        private int userId;
        private int docId;
        private boolean isNew;
        private void putToMap(Integer field, ConcurrentHashMap<Integer, List<Item>> map) {
            List<Item> items = map.get(field);
            if (items == null)
                items = new ArrayList<>();
            items.add(this);
            map.put(field, items);
        }
        public Item(WebSocketServer server, int userId, int docId, boolean isNew) {
            this.server = server;
            this.userId = userId;
            this.docId = docId;
            this.isNew = isNew;
            putToMap(this.userId, userItemMap);
            putToMap(this.docId, docItemMap);
        }
        public WebSocketServer getServer() { return server; }
        public void setServer(WebSocketServer server) { this.server = server; }
        public int getUserId() { return userId; }
        public void setUserId(int userId) { this.userId = userId; }
        public int getDocId() { return docId; }
        public void setDocId(int docId) { this.docId = docId; }
        public boolean isNew() { return isNew; }
        public void setNew(boolean aNew) { isNew = aNew; }
    }
    public synchronized void newClient(int userId, int docId, WebSocketServer server) {
        new Item(server, userId, docId, true);
    }
    public synchronized void clearClient(int userId, int docId) {
        List<Item> userItems = userItemMap.get(userId);
        List<Item> docItems = docItemMap.get(docId);
        Item target = get(userId, docId);
        userItems.remove(target);
        docItems.remove(target);
    }
    public synchronized Item get(int userId, int docId) {
        List<Item> userItems = userItemMap.get(userId);
        List<Item> docItems = docItemMap.get(docId);
        userItems.retainAll(docItems);
        return userItems.get(0);
    }
}
