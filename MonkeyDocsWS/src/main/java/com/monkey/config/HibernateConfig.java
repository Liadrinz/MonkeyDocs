package com.monkey.config;

import com.monkey.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.metamodel.Metadata;
import org.hibernate.metamodel.MetadataSources;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;

@Configuration
public class HibernateConfig {
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
}
