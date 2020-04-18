package com.monkey.pdu;

import java.io.Serializable;

public abstract class PDU implements Serializable {
    public enum Type {
        REQ, RES, ACK, MOD
    }
}
