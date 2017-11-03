package com.lmml.graph;

import org.postgresql.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@EnableJpaRepositories(basePackages = {"com.geem.repository"})
@EnableTransactionManagement
@EnableAspectJAutoProxy
@PropertySource({"classpath:config/db.properties"})
public class JPAConfig {

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() throws SQLException {
        System.out.println("SQLException");
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        try {
            Class<Driver> driverClass = (Class<Driver>) Class.forName(env.getProperty("db.driver"));
            dataSource.setDriverClass(driverClass);
            dataSource.setUrl(env.getProperty("db.url"));
            dataSource.setUsername(env.getProperty("db.username"));
            dataSource.setPassword(env.getProperty("db.password"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataSource;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() throws SQLException {
        System.out.println("entityManagerFactory");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(false);
        vendorAdapter.setShowSql(false);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.geem");
        factory.setDataSource(dataSource());
        factory.afterPropertiesSet();

        return factory.getObject();
    }

    @Bean
    public EntityManager entityManager(EntityManagerFactory entityManagerFactory) {
        System.out.println("entityManager");
        return entityManagerFactory.createEntityManager();
    }

    @Bean
    public PlatformTransactionManager transactionManager() throws SQLException {
        System.out.println("transactionManager");
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory());
        return txManager;
    }

    @Bean
    public HibernateExceptionTranslator hibernateExceptionTranslator() {
        System.out.println("hibernateExceptionTranslator");
        return new HibernateExceptionTranslator();
    }
    @Bean
    public MultipartResolver multipartResolver() {
        System.out.println("multipartResolver");
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(104857600);
        return multipartResolver;
    }
}