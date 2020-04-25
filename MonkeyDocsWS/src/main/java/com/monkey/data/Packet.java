package com.monkey.data;

public class Packet {
    private int userId;
    private int docId;
    private String type;
    private String update;
    private String content;

    public Packet(int userId, int docId, String type, String update, String content) {
        this.userId = userId;
        this.docId = docId;
        this.type = type;
        this.update = update;
        this.content = content;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDocId() {
        return docId;
    }

    public void setDocId(int docId) {
        this.docId = docId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
