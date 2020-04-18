package com.monkey.pdu;

public class Packet extends PDU {
    private long wsId;
    private long timestamp;
    private int docId;
    private int userId;
    private Type type;
    private int row;
    private int col;
    private String modify;
    private String content;

    public Packet() {}

    public Packet(long wsId, long timestamp, int docId, int userId, Type type, int row, int col, String modify, String content) {
        this.wsId = wsId;
        this.timestamp = timestamp;
        this.docId = docId;
        this.userId = userId;
        this.type = type;
        this.row = row;
        this.col = col;
        this.modify = modify;
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Packet that = (Packet) o;
        return timestamp == that.timestamp &&
                docId == that.docId &&
                userId == that.userId &&
                row == that.row &&
                col == that.col &&
                type == that.type &&
                modify.equals(that.modify) &&
                content.equals(that.content);
    }

    public long getWsId() {
        return wsId;
    }

    public void setWsId(long wsId) {
        this.wsId = wsId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getDocId() {
        return docId;
    }

    public void setDocId(int docId) {
        this.docId = docId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public String getModify() {
        return modify;
    }

    public void setModify(String modify) {
        this.modify = modify;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
