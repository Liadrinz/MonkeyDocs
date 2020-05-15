package com.monkey.factory;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

@Component
public class JedisFactory {
    private static Properties redisProperties = null;
    private static String ADDR;
    private static int PORT;
    private static String AUTH;
    private static int DB;
    private static int TIMEOUT;
    private static JedisPool pool = null;
    static {
        try {
            redisProperties = new Properties();
            redisProperties.load(new InputStreamReader(Object.class.getResourceAsStream("/redis.properties"), StandardCharsets.UTF_8));
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(Integer.parseInt(redisProperties.getProperty("max_active")));
            config.setMaxIdle(Integer.parseInt(redisProperties.getProperty("max_idle")));
            config.setMaxWaitMillis(Integer.parseInt(redisProperties.getProperty("max_wait")));
            ADDR = redisProperties.getProperty("address");
            PORT = Integer.parseInt(redisProperties.getProperty("port"));
            AUTH = redisProperties.getProperty("auth");
            DB = Integer.parseInt(redisProperties.getProperty("db"));
            TIMEOUT = Integer.parseInt(redisProperties.getProperty("timeout"));
            pool = new JedisPool(config, ADDR, PORT, TIMEOUT, AUTH, DB);
        } catch (IOException e) {
            System.err.println("Unable to load redis.properties");
            e.printStackTrace();
        }
    }
    public synchronized Jedis getJedis() {
        try {
            if (pool != null) {
                Jedis resource = pool.getResource();
                return resource;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public synchronized void returnResource(final Jedis jedis) {
        if (jedis != null) {
            pool.returnResource(jedis);
        }
    }
}
