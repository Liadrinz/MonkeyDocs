package com.monkey.entity;

import java.util.Date;

@Deprecated
public class Packet {
    private String kind;
    private Date time;
    private Integer docId;
    private Integer userId;
    private String payload;

    public Packet() {}

    public Packet(String kind, Integer docId, Integer userId, String payload) {
        this.kind = kind;
        this.time = new Date();
        this.docId = docId;
        this.userId = userId;
        this.payload = payload;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getDocId() {
        return docId;
    }

    public void setDocId(Integer docId) {
        this.docId = docId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}
