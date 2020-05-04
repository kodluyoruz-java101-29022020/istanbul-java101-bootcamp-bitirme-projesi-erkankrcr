package org.kodluyoruz.javabootcamp.libraryproject.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
@Order(1)
@PropertySource({ "classpath:application.properties" })
public class HibernateConfig {
    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl(environment.getProperty("spring.datasource.url"));
        dataSource.setUsername(environment.getProperty("spring.datasource.username"));
        dataSource.setPassword(environment.getProperty("spring.datasource.password"));

        return dataSource;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() throws SQLException{
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(environment.getProperty("spring.jpa.show-sql",Boolean.class));
        vendorAdapter.setDatabase(Database.POSTGRESQL);

        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setJpaVendorAdapter(vendorAdapter);
        factoryBean.setPackagesToScan("org.kodluyoruz.javabootcamp.libraryproject.dao.entity");
        factoryBean.setDataSource(dataSource());
        factoryBean.afterPropertiesSet();
        factoryBean.setJpaProperties(additionalProperties());
        return factoryBean.getObject();
    }

    private final Properties additionalProperties(){
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", environment.getProperty("spring.jpa.hibernate.ddl-auto"));
        properties.setProperty("hibernate.dialect", environment.getProperty("spring.jpa.properties.hibernate.dialect"));
        properties.setProperty("hibernate.show_sql", environment.getProperty("spring.jpa.show-sql"));
        properties.setProperty("hibernate.format_sql", environment.getProperty("spring.jpa.properties.hibernate.format_sql"));

        return properties;
    }
}
