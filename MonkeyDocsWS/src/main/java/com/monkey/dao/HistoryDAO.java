package com.monkey.dao;

import com.google.gson.Gson;
import com.monkey.entity.Packet;
import com.monkey.factory.JedisFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

@Repository
public class HistoryDAO {
    @Autowired
    private JedisFactory jedisFactory;
    private final Gson gson = new Gson();
    private static final String prefix = "history-";
    public synchronized void push(Packet packet) {
        jedisFactory.getJedis().lpush(prefix + packet.getDocId(), gson.toJson(packet));
    }
    public synchronized String range(int docId) {
        List<String> results = jedisFactory.getJedis().lrange(prefix + docId, 0, -1);
        List<String> reversedResults = new ArrayList<>();
        for (int i = results.size() - 1; i >= 0; --i) {
            reversedResults.add(results.get(i));
        }
        return "[" + String.join(",", reversedResults) + "]";
    }
}
