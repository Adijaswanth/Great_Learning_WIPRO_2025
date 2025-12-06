package com.example.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan("com.example")
public class AppConfig {

    // 1. DataSource (H2 In-Memory)
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("org.h2.Driver");
        ds.setUrl("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
        ds.setUsername("sa");   // ✔ Correct for H2
        ds.setPassword("");
        return ds;
    }

    // 2. SessionFactory
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sf = new LocalSessionFactoryBean();
        sf.setDataSource(dataSource());
        sf.setPackagesToScan("com.example.entity");  // ✔ Better than annotatedClasses()
        sf.setHibernateProperties(hibernateProperties());
        return sf;
    }

    // 3. Hibernate Properties
    private Properties hibernateProperties() {
        Properties props = new Properties();
        props.put("hibernate.hbm2ddl.auto", "update");
        props.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        props.put("hibernate.show_sql", "true");       // ✔ Show SQL
        props.put("hibernate.format_sql", "true");     // ✔ Format SQL
        return props;
    }

    // 4. Transaction Manager
    @Bean
    public HibernateTransactionManager hibernateTransactionManager() {
        HibernateTransactionManager tm = new HibernateTransactionManager();
        tm.setSessionFactory(sessionFactory().getObject());
        return tm;
    }
}
