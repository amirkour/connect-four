
package org.amirk.games.connectfour.web.config;

import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {
                   "org.amirk.games.connectfour.web",
                   "org.amirk.games.connectfour.db"
               },
               excludeFilters = {
                   @ComponentScan.Filter(type=FilterType.ANNOTATION, value=EnableWebMvc.class)
               })
public class AppConfig {
    
    @Bean
    @Profile("dev")
    public Logger devLogger(){
        return LoggerFactory.getLogger(AppConfig.class);
    }
    
    @Bean
    @Profile("default")
    public DataSource jndiDataSource() throws Exception{
        Context ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/connectfour");
        
        return ds;
    }
    
    /*
     * For unit testing DAOs in the web environment, activate the dev profile
     * to get this dummy/simple data source from spring, and then you
     * don't have to worry about jndi and pooling and such.
     */
    @Bean
    @Profile("dev")
    public DataSource devDataSource() throws Exception{
        return new DriverManagerDataSource("jdbc:mysql://localhost:3306/connectfour","root","root");
    }
    
    // Spring recognizes this bean as a SessionFactory
    // TODO - understand how this works ...
    @Bean
    @Profile("default")
    public LocalSessionFactoryBean localSessionFactoryBean(DataSource ds){
        
        LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
        
        lsfb.setDataSource(ds);
        
        // have to scan the beans, since there are no mapping files
        lsfb.setPackagesToScan("org.amirk.games.connectfour.entities");
        Properties props = new Properties();
        
        // from hibernate manual section 3.3:
        // For use inside an application server, you should almost always configure Hibernate 
        // to obtain connections from an application server javax.sql.Datasource registered in JNDI. 
        // You will need to set at least one of the following properties ...
        props.setProperty("hibernate.connection.datasource", "java:comp/env/jdbc/connectfour");

        // probably will always need the dialect set
        props.setProperty("hibernate.dialect","org.hibernate.dialect.MySQLDialect");

        // meh - don't care about these for now.  use the sql for debugging if you want
        props.setProperty("hibernate.cache.provider_class","org.hibernate.cache.internal.NoCacheProvider");
        props.setProperty("hibernate.show_sql", "false");

        lsfb.setHibernateProperties(props);
        
        return lsfb;
    }
    
    /*
     * In conjunction with the 'dev' data source, this session factory
     * is suitable for use when testing DAOs in a web environment. Just
     * active the 'dev' profile - note that this session factory differs
     * from the prod one in these noteable ways:
     * - sql logging is enabled in hibernate
     * - the jndi url is replaced w/ simple username/password
     */
    @Bean
    @Profile("dev")
    public LocalSessionFactoryBean devLocalSessionFactoryBean(DataSource ds){
        
        LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
        
        lsfb.setDataSource(ds);
        
        // have to scan the beans, since there are no mapping files
        lsfb.setPackagesToScan("org.amirk.games.connectfour.entities");
        Properties props = new Properties();
        
        // from hibernate manual section 3.3:
        // For use inside an application server, you should almost always configure Hibernate 
        // to obtain connections from an application server javax.sql.Datasource registered in JNDI. 
        // You will need to set at least one of the following properties ...
        props.setProperty("hibernate.connection.username", "root");
        props.setProperty("hibernate.connection.password", "root");

        // probably will always need the dialect set
        props.setProperty("hibernate.dialect","org.hibernate.dialect.MySQLDialect");

        // sql is shown by default in the 'dev' profile!
        props.setProperty("hibernate.cache.provider_class","org.hibernate.cache.internal.NoCacheProvider");
        props.setProperty("hibernate.show_sql", "true");

        lsfb.setHibernateProperties(props);
        
        return lsfb;
    }
    
    // Notice that the sesion factory is being wired-up from LocalSessionFactoryBean
    // in this class.
    // TODO - figure out how that works - spring somehow resolves the LSFB to a session factory ...?
    @Bean
    public PlatformTransactionManager txManager(SessionFactory sf){
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sf);
        return txManager;
    }
    
    /*
     * In conjunction with @Repository annotations, this bean will provide
     * automatic exception mapping.  It basically creates aop advisors for
     * anything with a @Repository annotation that will give you exception
     * mapping for hibernate exceptions.
     */
    @Bean
    public BeanPostProcessor persistenceTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }
}

