package com.gov.app.web.config;

import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.StringUtils;

@Configuration
@EnableTransactionManagement
@ComponentScan({ "com.gov.app.web.config" })
@PropertySource(value = { "classpath:jdbc.properties" })
public class MyHibernateConfig {

    @Autowired
    private Environment environment;

    final static Logger logger = Logger.getLogger(MyHibernateConfig.class.getName());

    //CONFIGURASI FOR DATABASE OFM ---------------------------------------------------------------------
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(OFMDataSource());
        sessionFactory.setPackagesToScan(new String[] { "com.gov.app.web.model" });
        sessionFactory.setHibernateProperties(hibernateProperties());

        Properties props = sessionFactory.getHibernateProperties();

        props.put("hibernate.format_sql", true);
        props.put("hibernate.show_sql", true);
        props.put("hibernate.jdbc.batch_size", 20);
        props.put("hibernate.dialect", "org.hibernate.dialect.Oracle9iDialect");
        return sessionFactory;
     }


     @Bean
     public DataSource OFMDataSource(){
         System.out.println("**********REPOS CONFIG**********");

         DataSource dataSource = null;
         String driverClass = environment.getProperty("jdbc.driverClassName");
         String jndiName = environment.getProperty("jdbc.jndi");

         if (!StringUtils.isEmpty(driverClass)) {
             try {
                 Class.forName(driverClass);
             } catch (Exception e) {
                 logger.error("Failed to load JDBC Driver " + driverClass, e);
             }
         }

         System.out.println("**********JNDI**********" + jndiName);

         if (jndiName != null && !jndiName.trim().isEmpty()) {
             try {
                 Context ctx = new InitialContext();
                 dataSource = (DataSource) ctx.lookup(jndiName);

                 System.out.println("**********REPOS CONFIG - CREATE JNDI CONNECTION **********");

             } catch (NamingException ne) {
                 logger.warn("Could not find JNDI : " + jndiName + " fallback to local settings");

             }
         }

         if (dataSource == null) {
             dataSource = createDataSource();
         }

         if (dataSource == null) {
             throw new RuntimeException("DataSource is not configured properly. Check your datasource/JNDI settings");
         }

         logger.info("Bean dataSource initialization done");

         return dataSource;
     }

    private DataSource createDataSource() {

        System.out.println("**********REPOS CONFIG - CREATE LOCAL CONNECTION **********");
        String url = environment.getProperty("jdbc.url");
        String username = environment.getProperty("jdbc.username");
        String password = environment.getProperty("jdbc.password");
        System.out.println("Con ------------ >> :" + environment.getProperty("jdbc.url").toString());

        if (StringUtils.isEmpty(url)) {
            return null;
        }

        BasicDataSource bds = new BasicDataSource();
        bds.setUrl(url);
        bds.setUsername(username);
        bds.setPassword(password);

        return bds;
    }

//    @Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
//        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
//        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
//        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
//        return dataSource;
//    }

//    @Bean(name = "OFMTrxMgr")
//    public PlatformTransactionManager getHibernateTransactionManagerOFM(SessionFactory sessionFactory) {
//        org.springframework.orm.hibernate5.HibernateTransactionManager trxMgr = new org.springframework.orm.hibernate5.HibernateTransactionManager(sessionFactory);
//    return trxMgr;
//}
    //END CONFIG FOR DATABASE OFM ---------------------------------------------------------------------
    
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        return properties;        
    }
    
	@Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s) {
       HibernateTransactionManager txManager = new HibernateTransactionManager();
       txManager.setSessionFactory(s);
       return txManager;
    }
}

