package com.monkey.service; 

import com.monkey.manager.ConnectionItem;
import com.monkey.pdu.PDUTest;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

public class HandlerTest {
    private final ServiceUnit unit = new ServiceUnit();
    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void testHandleRequest() throws Exception {
        long wsId = PDUTest.requestPacket.getWsId();
        unit.onReceive(PDUTest.requestPacket);
        ConnectionItem item = unit.getManager().getClient(wsId);
        assert item != null;
        assert !item.isSuccess();
        assert item.isLatest();
    }

    @Test
    public void testHandleACK() throws Exception {
        testHandleRequest();
        long wsId = PDUTest.ackPacket.getWsId();
        unit.onReceive(PDUTest.ackPacket);
        ConnectionItem item = unit.getManager().getClient(wsId);
        assert item != null;
        assert item.isSuccess();
        assert item.isLatest();
    }

    @Test
    public void testHandleModification() throws Exception {

    }
} 
