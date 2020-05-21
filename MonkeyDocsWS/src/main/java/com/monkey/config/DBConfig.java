package com.monkey.config;

import com.monkey.entity.*;
import com.monkey.factory.JedisFactory;
import org.hibernate.SessionFactory;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

@Configuration
public class DBConfig {
    @Bean
    public SessionFactory getSessionFactory() {
        org.hibernate.cfg.Configuration cfg = new org.hibernate.cfg.Configuration();
        cfg.addAnnotatedClass(Meta.class);
        cfg.addAnnotatedClass(User.class);
        cfg.addAnnotatedClass(MetaToUser.class);
        cfg.addAnnotatedClass(Delta.class);
        cfg.addAnnotatedClass(Checkpoint.class);
        cfg.configure();
        ServiceRegistry bulidServiceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
        return cfg.buildSessionFactory(bulidServiceRegistry);
    }
    @Bean
    public Jedis getJedis() {
        return new JedisFactory().getJedis();
    }
}
