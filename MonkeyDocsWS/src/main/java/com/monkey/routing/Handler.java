package com.monkey.routing;

import com.google.gson.Gson;
import com.monkey.data.Packet;
import com.monkey.service.ModService;
import com.monkey.service.ReqService;

import javax.annotation.Resource;

public class Handler {
    @Resource
    private ReqService reqService;
    @Resource
    private ModService modService;
    private static final Gson gson = new Gson();
    private static final ClientManager clientManager = new ClientManager();
    public String handle(String message) {
        Packet incoming = gson.fromJson(message, Packet.class);
        Packet result;
        switch (incoming.getType().toLowerCase()) {
            case "req":
                result = reqService.serve(incoming);
                return gson.toJson(result);
            case "mod":
                result = modService.serve(incoming);
                return gson.toJson(result);
            default:
                return null;
        }
    }
}
