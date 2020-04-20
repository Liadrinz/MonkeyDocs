package com.monkey.service.protocol;

import com.monkey.pdu.Packet;

public interface Protocol {
    void handle(String message);
    Packet receive();
}
