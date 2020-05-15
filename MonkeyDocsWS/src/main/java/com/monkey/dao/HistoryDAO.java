package com.monkey.dao;

import com.google.gson.Gson;
import com.monkey.entity.Packet;
import com.monkey.factory.JedisFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class HistoryDAO {
    private final Jedis jedis = new JedisFactory().getJedis();
    private final Gson gson = new Gson();
    private static final String prefix = "history-";
    public synchronized void push(Packet packet) {
        jedis.lpush(prefix + packet.getDocId(), gson.toJson(packet));
    }
    public synchronized List<String> list(int docId) {
        return jedis.lrange(prefix + docId, 0, -1);
    }
    public synchronized String range(int docId) {
        List<String> results = jedis.lrange(prefix + docId, 0, -1);
        return wrapResults(results);
    }
    private synchronized String wrapResults(List<String> results) {
        List<String> reversedResults = new ArrayList<>();
        for (int i = results.size() - 1; i >= 0; --i) {
            reversedResults.add(results.get(i));
        }
        return "[" + String.join(",", reversedResults) + "]";
    }
}
