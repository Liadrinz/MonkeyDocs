package com.monkey.pdu;

public class ConnectionItem {
    private long wsId;
    private int docId;
    private int userId;
    private long timestamp;
    private boolean latest;
    private boolean success;

    public ConnectionItem() {
    }

    public ConnectionItem(long wsId, int docId, int userId, long timestamp, boolean latest, boolean success) {
        this.wsId = wsId;
        this.docId = docId;
        this.userId = userId;
        this.timestamp = timestamp;
        this.latest = latest;
        this.success = success;
    }

    public long getWsId() {
        return wsId;
    }

    public void setWsId(long wsId) {
        this.wsId = wsId;
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

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isLatest() {
        return latest;
    }

    public void setLatest(boolean latest) {
        this.latest = latest;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
