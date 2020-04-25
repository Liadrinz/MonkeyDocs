package com.monkey.service.base;

import com.monkey.data.Packet;

public abstract class IncomingService {
    public abstract Packet serve(Packet incoming);
}
