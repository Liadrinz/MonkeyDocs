package com.monkey.dao;

import com.alibaba.fastjson.JSON;
import com.monkey.entity.Delta;
import com.monkey.entity.Packet;
import com.monkey.factory.JedisFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class HistoryDAO {
    public static final String prefix = "history-";
    public void push(Delta delta) {
        Jedis jedis = new JedisFactory().getJedis();
        jedis.lpush(prefix + delta.getDocid(), JSON.toJSONString(delta));
    }
    public synchronized void pushes(int docId, List<String> deltas) {
        Jedis jedis = new JedisFactory().getJedis();
        for (String delta : deltas) {
            jedis.lpush(prefix + docId, delta);
        }
    }
    public synchronized void push(int docId, List<Delta> deltas) {
        Jedis jedis = new JedisFactory().getJedis();
        for (Delta delta : deltas) {
            jedis.lpush(prefix + docId, JSON.toJSONString(delta));
        }
    }
    public void del(int docId) {
        Jedis jedis = new JedisFactory().getJedis();
        jedis.del(prefix + docId);
    }
    public List<String> list(int docId) {
        Jedis jedis = new JedisFactory().getJedis();
        return wrap(jedis.lrange(prefix + docId, 0, -1));
    }
    public String range(int docId) {
        Jedis jedis = new JedisFactory().getJedis();
        List<String> results = jedis.lrange(prefix + docId, 0, -1);
        return wrapResults(results);
    }
    private List<String> wrap(List<String> results) {
        List<String> reversedResults = new ArrayList<>();
        for (int i = results.size() - 1; i >= 0; --i) {
            reversedResults.add(results.get(i));
        }
        return reversedResults;
    }
    private String wrapResults(List<String> results) {
        return "[" + String.join(",", wrap(results)) + "]";
    }
}
