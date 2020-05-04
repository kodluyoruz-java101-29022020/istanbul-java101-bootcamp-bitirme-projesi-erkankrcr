package org.kodluyoruz.javabootcamp.libraryproject.application;

import org.hibernate.SessionFactory;
import org.kodluyoruz.javabootcamp.libraryproject.dao.HibernateConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.persistence.EntityManagerFactory;

@Configuration
@Order(2)
@ComponentScan(basePackages = {
        "org.kodluyoruz.javabootcamp.libraryproject.dao",
        "org.kodluyoruz.javabootcamp.libraryproject.dao.impl",
        "org.kodluyoruz.javabootcamp.libraryproject.application",
        "org.kodluyoruz.javabootcamp.libraryproject.config",
        "org.kodluyoruz.javabootcamp.libraryproject.service",
        "org.kodluyoruz.javabootcamp.libraryproject.service.impl",
        "org.kodluyoruz.javabootcamp.libraryproject.controller",
        "org.kodluyoruz.javabootcamp.libraryproject.annotion",
        "org.kodluyoruz.javabootcamp.libraryproject.aspect",
        "org.kodluyoruz.javabootcamp.libraryproject.util",
})
@Import(HibernateConfig.class)
@EnableWebMvc
@EnableAspectJAutoProxy
@PropertySource({ "classpath:application.properties" })
public class ApplicationConfig {
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Bean
    @Primary
    public SessionFactory getSessionFactory(){
        if (entityManagerFactory.unwrap(SessionFactory.class) == null) {
            throw new NullPointerException("kurucu bir hibernate kurucusu değil");
        }
        return entityManagerFactory.unwrap(SessionFactory.class);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
