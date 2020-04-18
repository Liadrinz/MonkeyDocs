package com.monkey.pdu;

import com.google.gson.Gson;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

public class PacketTest {
    static Gson gson = new Gson();

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void testSerializable() throws Exception {
        String jsonString = gson.toJson(PDUTest.requestPacket);
        Packet newPacket = gson.fromJson(jsonString, Packet.class);
        assert newPacket.equals(PDUTest.requestPacket);
    }

    @Test
    public void testUnserializable() {
        Packet packet = gson.fromJson(PDUTest.requestDataRaw, Packet.class);
        assert gson.toJson(packet).equals(PDUTest.requestDataRaw);
    }
} 
