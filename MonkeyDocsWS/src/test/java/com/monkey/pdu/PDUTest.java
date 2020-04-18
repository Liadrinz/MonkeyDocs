package com.monkey.pdu;

import com.google.gson.Gson;

public class PDUTest {
    public static Gson gson = new Gson();
    public static Packet requestPacket = new Packet(1, System.currentTimeMillis(), 1, 1, PDU.Type.REQ, 0, 0, "", "");
    public static String requestDataRaw = gson.toJson(requestPacket);
    public static Packet ackPacket = new Packet(1, System.currentTimeMillis(), 1, 1, PDU.Type.ACK, 0, 0, "", "");
    public static Packet responsePacket = new Packet(1, System.currentTimeMillis(), 1, 1, PDU.Type.RES, 0, 0, "", "# Hello, world!\n\n## Hi, monkey!");
    public static Packet modificationPacket = new Packet(1, System.currentTimeMillis(), 1, 1, PDU.Type.MOD, 0, 2, "+Hi! ", "");
} 
