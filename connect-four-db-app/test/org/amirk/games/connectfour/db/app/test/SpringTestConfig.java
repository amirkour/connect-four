
package org.amirk.games.connectfour.db.app.test;

import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringTestConfig {
    
    @Bean
    public SessionFactory sessionFactory(){
        
        org.hibernate.cfg.Configuration cfg = new org.hibernate.cfg.Configuration();
        cfg.addPackage("org.amirk.games.connectfour.entities");
        
        Properties props = new Properties();
        props.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        props.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/connectfour");
        props.setProperty("hibernate.connection.username", "root");
        props.setProperty("hibernate.connection.password", "root");
        props.setProperty("hibernate.connection.pool_size", "1");
        props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        props.setProperty("hibernate.current_session_context_class", "thread");
        props.setProperty("hibernate.cache.provider_class", "org.hibernate.cache.internal.NoCacheProvider");
        props.setProperty("hibernate.show_sql", "false");
        
        cfg.setProperties(props);
        
        StandardServiceRegistry svcReg = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties())
                                                                             .build();

        return cfg.buildSessionFactory( svcReg );
    }
}
