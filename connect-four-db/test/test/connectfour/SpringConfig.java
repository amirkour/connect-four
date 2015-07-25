/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.connectfour;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"org.amirk.games.connectfour", "test.connectfour.support"})
public class SpringConfig {
    
//    @Bean
//    public DataSource dataSource(){
//        BasicDataSource ds = new BasicDataSource();
//        ds.setUrl("jdbc:mysql://localhost:3306/connectfour");
//        ds.setUsername("root");
//        ds.setPassword("root");
//        ds.setDriverClassName("com.mysql.jdbc.Driver");
//        
//        return ds;
//    }
//    
//    @Bean
//    public LocalSessionFactoryBean sessionFactory(DataSource ds){
//        LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
//        lsfb.setDataSource(ds);
//        
//        // have to scan the beans, since there are no mapping files
//        lsfb.setPackagesToScan("org.amirk.games.connectfour.pojos");
//        
//        Properties props = new Properties();
//        
//        // db connection settings and driver.
//        // may or may not be necessary if you have/use jndi
////        props.setProperty("hibernate.connection.driver_class","com.mysql.jdbc.Driver");
////        props.setProperty("hibernate.connection.url","jdbc:mysql://localhost:3306/connectfour");
////        props.setProperty("hibernate.connection.username","root");
////        props.setProperty("hibernate.connection.password","root");
//
//        // probably will always need the dialect set
//        props.setProperty("hibernate.dialect","org.hibernate.dialect.MySQLDialect");
//
//        // meh - don't care about these for now.  use the sql for debugging if you want
//        props.setProperty("hibernate.cache.provider_class","org.hibernate.cache.internal.NoCacheProvider");
//        props.setProperty("hibernate.show_sql", "false");
//
//        // connection-pool stuff - will change from app to app
//        // see section 3.3 in the hibernate manual
////        props.setProperty("hibernate.connection.pool_size", "1");
////        props.setProperty("hibernate.c3p0.min_size", "5");
////        props.setProperty("hibernate.c3p0.max_size", "2");
////        props.setProperty("hibernate.c3p0.timeout", "1800");
////        props.setProperty("hibernate.c3p0.max_statements", "50");
////        props.setProperty("hibernate.c3p0.idle_test_period", "3000");
//
//        // session context is only necessary in non-web-app (i think)
//        props.setProperty("hibernate.current_session_context_class", "thread");
//        
//        lsfb.setHibernateProperties(props);
//        
//        return lsfb;
//    }
    
    @Bean
    public org.hibernate.cfg.Configuration hibConfig(){
        org.hibernate.cfg.Configuration cfg = new org.hibernate.cfg.Configuration();
        
        // since there are no xml mapping files, let hibernate know where my POJO entities are
        cfg.addPackage("org.amirk.games.connectfour.pojos")
           
           // db connection settings and driver.
           // may or may not be necessary if you have/use jndi
           .setProperty("hibernate.connection.driver_class","com.mysql.jdbc.Driver")
           .setProperty("hibernate.connection.url","jdbc:mysql://localhost:3306/connectfour")
           .setProperty("hibernate.connection.username","root")
           .setProperty("hibernate.connection.password","root")
           
           // probably will always need the dialect set
           .setProperty("hibernate.dialect","org.hibernate.dialect.MySQL5Dialect")
           
           // meh - don't care about these for now.  use the sql for debugging if you want
           .setProperty("hibernate.cache.provider_class","org.hibernate.cache.internal.NoCacheProvider")
           .setProperty("hibernate.show_sql", "false")
           
           // connection-pool stuff - will change from app to app
           // see section 3.3 in the hibernate manual
           .setProperty("hibernate.connection.pool_size", "1")
           .setProperty("hibernate.c3p0.min_size", "5")
           .setProperty("hibernate.c3p0.max_size", "2")
           .setProperty("hibernate.c3p0.timeout", "1800")
           .setProperty("hibernate.c3p0.max_statements", "50")
           .setProperty("hibernate.c3p0.idle_test_period", "3000")
           
           // session context is only necessary in non-web-app (i think)
           .setProperty("hibernate.current_session_context_class", "thread");
        
        return cfg;
    }
    
    @Bean
    public SessionFactory sessionFactory(org.hibernate.cfg.Configuration cfg){
        StandardServiceRegistry svcReg = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties())
                                                                             .build();

        SessionFactory sf = cfg.buildSessionFactory( svcReg );
        return sf;
    }
}
