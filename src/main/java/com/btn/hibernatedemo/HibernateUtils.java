package com.btn.hibernatedemo;
import org.hibernate.SessionFactory;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtils {
    // Tạo đối tượng Factory
    private static final SessionFactory factory;

    static {
        Configuration conf = new Configuration();
        Properties props = new Properties();
        props.put(Environment.DRIVER,"commysysql.cj.jdbc.Driver");
        props.put(Environment.URL,"jdbc:mysql://localhost/saledb");
        props.put(Environment.USER,"root");
        props.put(Environment.PASS,"Admin@123");
        props.put(Environment.SHOW_SQL,"true");

        ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
        factory = conf.buildSessionFactory(registry);
    }

    public static SessionFactory getFactory() {
        return factory;
    }
}
